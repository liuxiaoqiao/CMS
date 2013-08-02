package com.foxconn.util;

import java.io.BufferedReader;   
import java.io.File;   
import java.io.IOException;   
import java.io.InputStreamReader;   


  
/**   
 *   
 * @version 1.0  
 * @author lvzf 
 */  
public class PdfToSwf {   
	//final String source=SystemConfig.getInstance().getProperty("PDFTOSWF_PATH");
	final String source = "C:/Program Files/SWFTools>pdf2swf.exe"; 
	public void convertPDF2SWF(String fromPath ,String toPath) throws IOException {
		Runtime.getRuntime().exec("/"+source+" -z -s  flashversion=10 "+fromPath+" -o "+toPath);
	}
    int convertPDF2SWF(String sourcePath, String destPath,   
            String fileName) throws IOException {   
        // 目标路径不存在则建立目标路径  
        File dest = new File(destPath);   
        if (!dest.exists()) {   
            dest.mkdirs();   
        }   
  
        // 源文件不存在则返回 
        File source = new File(sourcePath);   
        if (!source.exists()) {   
            return 0;   
        }   
  
       // 调用pdf2swf命令进行转换   
        // C:\Program Files\SWFTools>pdf2swf.exe -z -B rfxview.swf -s flashversion=9   
        // d:/人员管理系   
        // 统PersonalManagementSystem简介.pdf -o d:/test.swf   
  
        // 要把D:\\tools\\SWFTools\\放在path里面……不然使用不了播放器   
  
        // 先生成flash   
 
//        String[] envp = new String[1];   
//        envp[0] = "PATH=C:\\Program Files\\SWFTools\\";  
//        //-z -s flashversion=9   -T 9
//        String command = "pdf2swf \"" + sourcePath   
//                + "\" -o \"" + destPath + fileName + "\"";   
  
        String[] envp = new String[1]; 
        envp[0] = "PATH=D:\\tools\\SWFTools\\"; 
//        String command = "pdf2swf -z -s flashversion=10 \"" + sourcePath 
//                + "\" -o \"" + destPath + fileName + "\"";
        String command = "C:\\Program Files\\SWFTools\\pdf2swf.exe" + " -o \"" + destPath + "\\" + fileName + "\"  -s languagedir=C:\\xpdf-chinese-simplified -s flashversion=9 \"" + sourcePath + "\"";
//        String command = "pdf2swf.exe" + sourcePath + "-o" + destPath + fileName + "-T 9 -f";
        
        Process pro = Runtime.getRuntime().exec(command, envp);   
        //Process pro = Runtime.getRuntime().exec("cmd /c  d:\\score.txt");  
        //Process pro  =Runtime.getRuntime().exec("/"+source+" d:\\displaytag.pdf -o d:\\displaytag.swf");
        System.out.println(command);   
        BufferedReader bufferedReader = new BufferedReader(   
                new InputStreamReader(pro.getInputStream()));   
        while (bufferedReader.readLine() != null) {   
            String text = bufferedReader.readLine();   
            System.out.println(text);   
        }   
        try {   

            pro.waitFor();    
        } catch (InterruptedException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
       // 然后在套播放器   
        /*  
         * swfcombine -z -X 720 -Y 540 "D:\tools\SWFTools\swfs\rfxview.swf"  
         * viewport="d:/人  
         * 员管理系统PersonalManagementSystem简介.swf" -o "d:/人员管理系统PersonalManagemen  
         * tSystem简介.swf"  
         */  
//        command = "swfcombine -z -X 720 -Y 540 \"C:/Program Files/SWFTools/swfs/rfxview.swf\" viewport=\""  
//                + destPath + fileName + "\" -o \"" + destPath + fileName + "\"";   
//        pro = Runtime.getRuntime().exec(command, envp);   
//        System.out.println(command);   
//        bufferedReader = new BufferedReader(new InputStreamReader(pro   
//                .getInputStream()));   
//        while (bufferedReader.readLine() != null) {   
//            String text = bufferedReader.readLine();   
//            System.out.println(text);   
//        }   
//        try {   
//            pro.waitFor();   
//        } catch (InterruptedException e) {   
//            // TODO Auto-generated catch block   
//            e.printStackTrace();   
//        }   

        return pro.exitValue();   
  
    }   
  
    public static void main(String[] args) {   
        String sourcePath = "D:/週報告/PMBOK2008.pdf";   
        String destPath = "D:/週報告/";   
        String fileName = "ORACLE.swf";   
        try {   
        	int r=new PdfToSwf().convertPDF2SWF(sourcePath,   
                    destPath, fileName);
            System.out.println(r);   
        	//new PdfToSwf().convertPDF2SWF("D:/週報告/第35周/ORACLE.pdf","D:/週報告/第35周/ORACLE.swf");
        } catch (IOException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
        
    }   
}  
