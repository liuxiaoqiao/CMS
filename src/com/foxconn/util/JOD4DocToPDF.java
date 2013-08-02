package com.foxconn.util;

import java.io.File;   
import java.io.IOException;
import java.net.ConnectException;   
import java.util.Date;   
import com.artofsolving.jodconverter.DocumentConverter; 
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;   
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;   
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter; 
/**
 * 
 * @author lvzf
 *
 */
public class JOD4DocToPDF extends java.lang.Thread {   
	//private static final Log log = LogFactory.getLog(JOD4DocToPDF.class);
	  private File inputFile;// 需要转换的文件    
	    private File outputFile;// 输出的文件    
	    @SuppressWarnings("unused")
		private Integer id;
	    public JOD4DocToPDF(File inputFile, File outputFile,Integer id) {   
	        this.inputFile = inputFile;   
	        this.outputFile = outputFile;  
	        this.id=id;
	    }   
	  
	    public void docToPdf() {  
	    	String sourcePath = "";
	    	String inputFilePath = inputFile.getAbsolutePath().toString();
	    	int temp = inputFilePath.lastIndexOf(".");
	    	String inputFileSuffix = inputFilePath.substring(temp+1, inputFilePath.length());
	    	if(!inputFileSuffix.equals("pdf")) {     	
	    		Date start = new Date();   
	    		// connect to an OpenOffice.org instance running on port 8100   
	    		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);   
	    		try {   
	    			connection.connect();    
	    			// convert   
	    			DocumentConverter converter = new OpenOfficeDocumentConverter(   
	                    connection);   
	    			converter.convert(inputFile, outputFile);  
	    		} catch (ConnectException cex) { 
	    			//cex.printStackTrace();   
	    			//log.error(cex);
	    		} finally {   
	    			// close the connection   
	    			if (connection != null) {   
	    				connection.disconnect();   
	    				connection = null;   
	    			}	   
	    		}   
	    		long l = (start.getTime() - new Date().getTime());   
	    		long day = l / (24 * 60 * 60 * 1000);   
	    		long hour = (l / (60 * 60 * 1000) - day * 24);   
	    		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);   
	    		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);   
	    		System.out.println("生成" + outputFile.getName() + "耗费：" + min + "分" + s   
	                + "秒");   	        	        	        	        	     
	    		//结束转换
	    		//DocService.getInstance().endConverPdfOrder(id);
	        	        	    	        
	        	sourcePath = outputFile.getAbsolutePath().toString();  
	    	} else {
	    		sourcePath = inputFile.getAbsolutePath().toString();     		
	    	}
	    	String destPath = "D:/FoxTool/Files/online/";   
    		//String destPath = "http://10.148.73.79:8080/foxtool/upload/";        
    		//String fileName = "ORACLE.swf";  
    		String fileName = outputFile.getName();
    		int tem = fileName.indexOf(".");
    		fileName = fileName.substring(0, tem);
    		fileName = fileName + ".swf";
            System.out.println("outpath==============================" + sourcePath);
    		
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
	  
	   /**  
	     * 由于服务是线程不安全的，所以……需要启动线程  
	     */  

	    public void run() {   
	        this.docToPdf();   
	  
	    }   
	  
	    public File getInputFile() {   
	        return inputFile;   
	    }   
	  
	    public void setInputFile(File inputFile) {   
	        this.inputFile = inputFile;   
	    }   
	  
	    public File getOutputFile() {   
	        return outputFile;   
	    }   
	  
	    public void setOutputFile(File outputFile) {   
	        this.outputFile = outputFile;   
	    }   
	  
	    /**  
	     * @param args  
	     */  
	    public static void main(String[] args) {   
	    	@SuppressWarnings("unused")
			boolean tend=false;
	        JOD4DocToPDF tools = new JOD4DocToPDF(new File("D:/週報告/第35周/ORACLE.docx"),   
	                new File("D:/週報告/第35周/ORACLE.pdf"),3);   
	        tools.start(); 
//	        File inputFile = new File("D:/週報告/第35周/動態加載js.doc");
//	        File outputFile = new File("D:/週報告/第35周/動態加載js.pdf"); 
//	        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
//	        try{
//	        connection.connect();
//	        }catch(Exception e){
//	         e.printStackTrace();
//	        }
//	        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
//	        converter.convert(inputFile, outputFile);
//	        connection.disconnect(); 
	    }   
  
} 