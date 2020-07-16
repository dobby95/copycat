package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    /*File s = new File("Y:\\data1\\researcher_data");
	    File t = new File("C:\\Users\\DEV_001\\Desktop\\기타\\강아지_귀_배_모음");
	    //Main.copy(s, t);
        File a = new File("C:\\Users\\DEV_001\\Desktop\\기타\\강아지 안구질환");
        //System.out.println(deleteEmptyFoler(t) + " 개 삭제");*/

        File t = new File("C:\\Users\\DEV_001\\Desktop\\merge_작업본\\3");
        //File a = new File("C:\\Users\\DEV_001\\Desktop\\merge\\0");

        fiveImg(t);

    }
    public static void fiveImg(File sourceF) {
        File[] ff = sourceF.listFiles();
        List<String> orgList = new ArrayList<>();
        for(File file : ff){
            String[] num = file.getName().split("_");
            //String ret1 = num[0];
            String ret2 = num[1];
            //String ret3 = num[2];
            boolean same = false;
            for(String a : orgList){
                if(ret2.equals(a)){
                    same = true;
                }
            }
            if(same == false){
                orgList.add(ret2);
            }
        }

        for(String a : orgList){
            int count = 0;
            for(File file : ff){
                String[] num = file.getName().split("_");
                String ret = num[1];
                if(a.equals(ret)){
                    if(count>4){
                        file.delete();
                        System.out.println(file.getName() + " 은 5장 이상이라 삭제합니다.");
                    }
                    else count++;
                }
            }
        }
    }


    public static void delImg(File sourceF, File targetF) {
        File[] ff = sourceF.listFiles();
        List<String> orgList = new ArrayList<>();
        for(File file : ff){
            String[] num = file.getName().split("_");
            //String ret1 = num[0];
            String ret2 = num[1];
            //String ret3 = num[2];
            orgList.add(ret2);
        }

        File[] tt = targetF.listFiles();
        for(File file : tt){
            String[] num = file.getName().split("_");
            String ret = num[1];
            for(String a:orgList){
                if(ret.equals(a)) {
                    //System.out.println(ret + "번은 " + a + " 와 중복됩니다.");
                    //System.out.println(file.getName() + "파일을 삭제할 예정입니다.");
                    file.delete();
                    System.out.println(file.getName() + " 은 " + a + "번이기 때문에 삭제했습니다.");

                }
            }
        }
    }

    public static void copy(File sourceF, File targetF){
        File[] ff = sourceF.listFiles();
        for (File file : ff) {
            File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
            if(file.isDirectory()){
                temp.mkdir();
                System.out.println("폳더 없음 함수 다시 불러옴");
                copy(file, temp);
            } else {
                if(file.getName().equals("earl.jpg") || file.getName().equals("earr.jpg") || file.getName().equals("belly.jpg")) {
                    FileInputStream fis = null;
                    FileOutputStream fos = null;
                    try {
                        fis = new FileInputStream(file);
                        fos = new FileOutputStream(temp);
                        byte[] b = new byte[4096];
                        int cnt = 0;
                        System.out.println(file.getName()+"복사중");
                        while ((cnt = fis.read(b)) != -1) {
                            fos.write(b, 0, cnt);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fis.close();
                            fos.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    public static int deleteEmptyFoler(File targetF){
        if(!targetF.isDirectory()) return 0;
        int delCnt = 0;
        for(File subfile:targetF.listFiles()){
            if(subfile.isDirectory()){
                delCnt += deleteEmptyFoler(subfile);
            }
        }
        if(targetF.listFiles().length == 0) {
            targetF.delete();
            delCnt++;
        }
        return delCnt;
    }
}
