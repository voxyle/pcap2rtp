package com.xp.jrtp;

import com.xp.jrtp.buffer.DataBuffer;
import com.xp.jrtp.header.IPHeader;
import com.xp.jrtp.header.PacketHeader;
import com.xp.jrtp.header.ProtocolData;
import com.xp.jrtp.header.UDPHeader;
import com.xp.jrtp.log.Trace;
import com.xp.jrtp.tool.TextTool;
import com.xp.jrtp.type.PacketType;
import com.xp.jrtp.type.ProtocolType;
import com.xp.jrtp.voip.Rtp;
import com.xp.jrtp.voip.Sip;
import com.xp.jrtp.voip.Voip;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xp.jrtp.tool.ByteTool.*;

public class Pcap {
    private static final int HEADLEN = 16;


    public static HashMap<String, Voip> parse(InputStream stream) throws IOException {
        //前24个字节是pcap的头,丢弃
        int rl = stream.read(new byte[24]);
        if (rl == -1) {
            return null;
        }
        byte[] header = new byte[HEADLEN];
        BufferExp exp = new BufferExp();
        int len, i = 0;
        while ((len = stream.read(header)) != -1) {
            Trace.i("process packet " + i++);
            if (len != HEADLEN) continue;
            exp.h(new DataBuffer(header))
                    .process();

            byte[] data = new byte[(int) exp.getClen()];
            int dlen = stream.read(data);
            if (dlen != exp.getClen()) {
                continue;
            }
            exp.d(new DataBuffer(data))
                    .process();

        }
        return exp.getVpMap();
    }

    private static class BufferExp {
        private static final Pattern from = Pattern.compile("^from:.*<sip:(.*)@.*>", Pattern.CASE_INSENSITIVE);
        private static final Pattern to = Pattern.compile("^to:.*<sip:(.*)@.*>", Pattern.CASE_INSENSITIVE);
        private static final Pattern cinfo = Pattern.compile("^c=.*");
        private static final Pattern minfo = Pattern.compile("^m=.*");
        private static final Pattern branch = Pattern.compile("branch=([0-9 \\- a-z A-Z]*)", Pattern.CASE_INSENSITIVE);

        //    //28字节
        private final static String FLAG_SESSION = "SIP/2.0 183 Session Progress";
        private final static String FLAG_INVITE = "INVITE";
        private final static String FLAG_SIP = "SIP";

        private enum Mode {
            HEADER(1),
            DATA(2);

            Mode(int value) {
            }
        }

        private DataBuffer buffer;
        private Mode mode;
        private HashMap<String, Voip> vpMap = new HashMap<>();
        private IPHeader ipHeader;
        private UDPHeader udpHeader;
        private ProtocolData protocolData;
        private PacketHeader packetHeader;

        /**
         * 开启 header模式
         */
        BufferExp h(DataBuffer buffer) {
            this.buffer = buffer;
            this.mode = Mode.HEADER;
            return this;
        }

        BufferExp d(DataBuffer buffer) {
            this.buffer = buffer;
            this.mode = Mode.DATA;
            return this;
        }

        void process() {
//            try {
            if (mode == Mode.HEADER) {
                readPacrketHeader();
            } else {
                readIPHeader();
                readPort();
                readData();
                clear();
            }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }

        private void clear() {
            packetHeader = null;
            protocolData = null;
            udpHeader = null;
        }

        void readPacrketHeader() {
            byte[] buffer_4 = new byte[4];
            packetHeader = new PacketHeader();
            buffer.getBuf_4(buffer_4);
            packetHeader.setTimeHi(toReverseHex(buffer_4));
            buffer.getBuf_4(buffer_4);
            packetHeader.setTimeLo(toReverseHex(buffer_4));
            buffer.getBuf_4(buffer_4);
            packetHeader.setcLen(toReverseHex(buffer_4));
            buffer.getBuf_4(buffer_4);
            packetHeader.setdLen(toReverseHex(buffer_4));
        }

        void readIPHeader() {
            byte[] buff_2 = new byte[2];
            byte[] buff_4 = new byte[4];
            //获取packet type
            buffer.getBuf_2(buff_2);
            int pt = Integer.parseInt(tohex(buff_2), 16);
            //获取所有发送的数据
            if (pt != PacketType.SEND.getType()) {
                return;
            }
            protocolData = new ProtocolData();
            //丢弃14字节的以太网帧头
            buffer.offset(14);
            //开始IP头解析
            ipHeader = new IPHeader();

            //获取版本和头长度
            byte vhl = buffer.get();
            ipHeader.setVarHLen(vhl);
            String bitTmp = byteToBit(vhl);
            int version = Integer.parseInt(bitTmp.substring(0, 4), 2);
            int hlength = Integer.parseInt(bitTmp.substring(4), 2);
            ipHeader.setVersion(version);
            ipHeader.sethLength(hlength);

            //获取服务类型
            ipHeader.setTos(buffer.get());

            //获取总长度
            buffer.getBuf_2(buff_2);
            ipHeader.setTotalLen(Integer.parseInt(tohex(buff_2), 16));

            //获取ip标识
            buffer.getBuf_2(buff_2);
            ipHeader.setId(Integer.parseInt(tohex(buff_2), 16));

            //标志与偏移量
            buffer.getBuf_2(buff_2);
            ipHeader.setFlagSegment(Integer.parseInt(tohex(buff_2), 16));

            //时间周期
            ipHeader.setTtl(buffer.get());

            byte protocol = buffer.get();
            ipHeader.setProtocol(protocol);
            protocolData.setProtocolType(ProtocolType.parseType(protocol));

            // header checksum
            buffer.getBuf_2(buff_2);
            ipHeader.setCheckSum(Integer.parseInt(tohex(buff_2), 16));

            // 拼接出 SourceIP
            buffer.getBuf_4(buff_4);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                builder.append((int) (buff_4[i] & 0xff));
                builder.append(".");
            }
            builder.deleteCharAt(builder.length() - 1);
            String sourceIP = builder.toString();
            ipHeader.setSrcIP(sourceIP);
            protocolData.setSrcIP(sourceIP);

            // 拼接出 DestinationIP
            buffer.getBuf_4(buff_4);
            builder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                builder.append((int) (buff_4[i] & 0xff));
                builder.append(".");
            }
            builder.deleteCharAt(builder.length() - 1);
            String destinationIP = builder.toString();
            ipHeader.setDstIP(destinationIP);
            protocolData.setDesIP(destinationIP);
        }

        void readPort() {
            if (protocolData == null) return;
            if (protocolData.getProtocolType() == ProtocolType.UDP) {
                readUdpHeader();
            }
        }

        void readUdpHeader() {
            byte[] buff_2 = new byte[2];
            udpHeader = new UDPHeader();
            buffer.getBuf_2(buff_2);
            int srcPort = Integer.parseInt(tohex(buff_2), 16);
            udpHeader.setSrcPort(srcPort);
            protocolData.setSrcPort(srcPort);

            buffer.getBuf_2(buff_2);
            int desPort = Integer.parseInt(tohex(buff_2), 16);
            udpHeader.setDstPort(desPort);
            protocolData.setDesPort(desPort);

            buffer.getBuf_2(buff_2);
            udpHeader.setLength(Integer.parseInt(tohex(buff_2), 16));

            buffer.getBuf_2(buff_2);
            udpHeader.setCheckSum(Integer.parseInt(tohex(buff_2), 16));
        }

        void readData() {
            if (udpHeader == null
                    || udpHeader.getLength() == 0) {
                return;
            }
            int size = udpHeader.getLength() - 8;
            byte[] tmpbs = new byte[size];
            buffer.get(tmpbs);
            String str = new String(tmpbs);
            if (str.contains(FLAG_SIP)
                    || str.contains(FLAG_SIP.toLowerCase())) {
                Sip sip = parse(str);
                if (sip != null &&
                        !TextTool.isEmty(sip.getCd()) &&
                        !TextTool.isEmty(sip.getmPort())) {
                    String key = sip.getCd() + "&" + sip.getmPort();
                    Voip voip = vpMap.get(key);
                    if (voip == null) {
                        voip = new Voip();
                        vpMap.put(key, voip);
                    }
                    voip.setSip(sip);
                    return;
                }
            } else {
                /**
                 * rtp格式至少12个字节
                 */
                if (size < 12) {
                    return;
                }
                Rtp rtp = parseRtp(new DataBuffer(tmpbs));
                if (rtp != null) {
                    String key = ipHeader.getDstIP() + "&" + udpHeader.getDstPort();
                    Voip voip = vpMap.get(key);
                    if (voip == null) {
                        voip = new Voip();
                        vpMap.put(key, voip);
                    }
                    voip.setRtp(rtp);
                }
            }

        }

        Rtp parseRtp(DataBuffer buffer) {
            Rtp rtp = new Rtp();
            byte[] buff_2 = new byte[2];
            byte[] buff_4 = new byte[4];
            String temp = byteToBit(buffer.get());
            int version = Integer.parseInt(temp.substring(0, 2), 2);
            if (version != 2) {
                return null;
            }
            rtp.setVersion(version);
            rtp.setPadding(Integer.parseInt(temp.substring(2, 3), 2));
            rtp.setExtHeader(Integer.parseInt(temp.substring(3, 4), 2));
            rtp.setCc(Integer.parseInt(temp.substring(4), 2));
            temp = byteToBit(buffer.get());
            rtp.setMaker(Integer.parseInt(temp.substring(0, 1), 2));
            rtp.setpType(Integer.parseInt(temp.substring(1), 2));
            buffer.getBuf_2(buff_2);
            rtp.setsNumber(Integer.parseInt(tohex(buff_2), 16));
            buffer.getBuf_4(buff_4);
            rtp.setTimestamp(Long.parseLong(tohex(buff_4), 16));
            buffer.getBuf_4(buff_4);
            rtp.setSsrc(tohex(buff_4));
            rtp.setPayload(buffer.array());
            return rtp;
        }

        Sip parse(String str) {
            Sip sip = null;
            sip = new Sip();
            sip.setBranch(regxOfBrackets(str, branch));
            sip.setFrom(regxOfBrackets(str, from));
            sip.setTo(regxOfBrackets(str, to));
            String cinTemp = regx(str, cinfo);
            if (!TextTool.isEmty(cinTemp)) {
                String[] temp = cinTemp.split(" ");
                sip.setCnType(temp[0]);
                try {
                    sip.setCdType(temp[1]);
                    sip.setCd(temp[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String minTemp = regx(str, minfo);
            if (!TextTool.isEmty(minTemp)) {
                String[] temp = minTemp.split(" ");
                switch (temp.length) {
                    case 5:
                        sip.setMf2(temp[4]);
                    case 4:
                        sip.setMf1(temp[3]);
                    case 3:
                        sip.setmProtocol(temp[2]);
                    case 2:
                        sip.setmPort(temp[1]);
                    case 1:
                        sip.setmType(temp[0]);
                }
            }
            return sip;
        }

        /**
         * 获取包数据的长度
         *
         * @return
         */
        long getClen() {
            return packetHeader.getcLen();
        }

        /**
         * 正则
         */
        String regx(String str, Pattern pattern) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return matcher.group();
            }
            return null;
        }

        private String regxOfBrackets(String str, Pattern pattern) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                if (matcher.groupCount() == 1) {
                    return matcher.group(1);
                }
            }
            return null;
        }

        HashMap<String, Voip> getVpMap() {
            return vpMap;
        }

    }
}