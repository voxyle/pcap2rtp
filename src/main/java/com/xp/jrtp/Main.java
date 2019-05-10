package com.xp.jrtp;

import com.xp.jrtp.tool.ByteTool;
import com.xp.jrtp.tool.CodesTool;
import com.xp.jrtp.type.PayloadType;
import com.xp.jrtp.voip.*;

import java.io.*;
import java.util.*;

import static com.xp.jrtp.type.PayloadType.g711;

public class Main {

    public static void main(String[] args) throws IOException {
        FileInputStream stream = null;

        try {
            stream = new FileInputStream("D:\\goProject\\penge\\ttt\\Downloads\\a99~\\a99~~\\a_w0.cap");
//            stream = new FileInputStream("D:\\goProject\\penge\\ttt\\Downloads\\a_w0.cap");
            new Entrance(Pcap.parse(stream))
                    .forMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

    private static class Entrance {
        private HashMap<String, Voip> vMap;
        private HashMap<Phone, List<byte[]>> ptpMap;
        private String basePath = "D:\\goProject\\rtp\\";
        private String path;


        public Entrance(HashMap<String, Voip> vMap) {
            this.vMap = vMap;
            this.ptpMap = new HashMap<>();
        }

        void forMap() {
            if (vMap == null) {
                new NullPointerException("");
            }
            Iterator<Map.Entry<String, Voip>> iterator = vMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Voip> entry = iterator.next();
                Voip voip = entry.getValue();
                List<Rtp> rtpList = voip.getRtp();
                Collections.sort(rtpList);
                byte[] array = new byte[0];
                String ssrc = null;
                int ptype = 0;
                for (Rtp rtp : rtpList) {
                    ssrc = rtp.getSsrc();
                    ptype = rtp.getpType();
                    if (array == null) {
                        array = rtp.getPayload();
                        continue;
                    }
                    array = ByteTool.mergeByte(array, rtp.getPayload());
                }
//                Sip sip = voip.getSip();
//                if (sip == null) {
//                    path = basePath + "noSip\\";
//                    mkdirs(path);
                exportPayload(array, ssrc, ptype);
//                } else {
//                    path = basePath + "Sip\\";
//                    mkdirs(path);
//                    exportPayload(array,ssrc,ptype);
//                    Phone phone = sip.getPhone();
//                    phone.setSsrc(ssrc);
//                    phone.setPt(ptype);
//                    List<byte[]> bList = ptpMap.get(phone);
//                    if (bList == null) {
//                        bList = new ArrayList<>();
//                        ptpMap.put(sip.getPhone(), bList);
//                    }
//                    bList.add(array);
            }
//            }
//            Iterator<Map.Entry<Phone, List<byte[]>>> bIterator = ptpMap.entrySet().iterator();
//            while (bIterator.hasNext()) {
//                Map.Entry<Phone, List<byte[]>> entry = bIterator.next();
//                Phone phone = entry.getKey();
//                List<byte[]> list = entry.getValue();
//                path = basePath+phone.getFrom()+"---"+phone.getTo()+"\\";
//                mkdirs(path);
//                for (byte[] bl:list){
//                    exportPayload(bl,phone.getSsrc(),phone.getPt());
//                }
//            }
        }

        private void exportPayload(byte[] array, String ssrc, int ptype) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(basePath + ssrc + "." + PayloadType.parseType(ptype) + "_" + ptype, true);
                byte[] bytes = check(array, ptype);
                fos.write(bytes);
                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private byte[] check(byte[] array, int ptype) {
            byte[] bytes = new byte[0];
            try {
                switch (PayloadType.parseType(ptype)) {
                    case g711:
                        bytes = CodesTool.g711DecodeByte(array);
                        break;
                    case g729:
                        bytes = CodesTool.g729DecodeByte(array);
                        break;
                    default:
                        bytes = array;
                        break;

                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return bytes;
        }

        private void mkdirs(String dir) {
            File file = new File(dir);
            if (file.exists()) {
                if (file.isFile())
                    file.mkdirs();
            } else {
                file.mkdirs();
            }
        }


    }

}
