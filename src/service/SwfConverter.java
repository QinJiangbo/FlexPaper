package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.star.io.IOException;

public class SwfConverter {
	public String convert(String filePath, String fileName) throws IOException, java.io.IOException{
		final String DOC = ".doc";
		final String DOCX = ".docx";
		final String XSL = ".xsl";
		final String XSLX = ".xslx";
		final String PDF = ".pdf";
		final String SWF = ".swf";
		final String TOOL = "G:/Development/SWFTools/pdf2swf.exe";
		String outFile = "";
		String fileNameOnly = "";
		String fileExt = "";
		if(null != fileName && fileName.lastIndexOf(".") > 0){
			int index = fileName.lastIndexOf(".");
			fileNameOnly = fileName.substring(0, index);
			fileExt = fileName.substring(index).toLowerCase();
		}
		String inputFile = filePath + File.separator + fileName;
		String outputFile = "";
		//if office document, convert to pdf file
		if(fileExt.equals(DOC) || fileExt.equals(DOCX) ||fileExt.equals(XSL) ||fileExt.equals(XSLX)){
			outputFile = filePath + File.separator + fileNameOnly + PDF;
			office2pdf(inputFile, outputFile);
			inputFile = outputFile;
			fileExt = PDF;
		}
		
		if(fileExt.equals(PDF)){
			String toolFile = filePath + File.separator + TOOL;
			outputFile = filePath + File.separator + fileNameOnly + SWF;
			pdf2swf(inputFile, outputFile, toolFile);
			outFile = outputFile;
		}
		return outFile;
	}

	private void pdf2swf(String sourceFile, String outFile, String toolFile) {
		String command = toolFile + "\"" + sourceFile + "\" -o \"" +outFile + "\" -s flashversion=9 ";
		try{
			Runtime.getRuntime().exec(command);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private int office2pdf(String srcFile, String destFile) throws IOException, java.io.IOException {
		//ResourceBundle rb = ResourceBundle.getBundle("OpenOfficeService");
		String OpenOffice_HOME = "G:/Development/OpenOffice 4/";
		String host_Str = "127.0.0.1"; //address
		String port_Str = "8100"; //port number
		try{
			File inputFile = new File(srcFile);
			if(!inputFile.exists()){
				return -1;
			}
			File outputFile = new File(destFile);
			if(!outputFile.getParentFile().exists()){
				outputFile.getParentFile().mkdirs();
			}
			
			String command = OpenOffice_HOME + "program/soffice.exe -headless -accept=\"socket,host=" + host_Str + ",port=" + port_Str + ";urp;\"";
			System.out.println(command);
			Process pro = Runtime.getRuntime().exec(command);
			//connect openoffice service
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(host_Str, Integer.parseInt(port_Str));
			connection.connect();
			//transfer
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			
			//close
			connection.disconnect();
			pro.destroy();
			return 0;
		}catch(FileNotFoundException ex){
			System.out.println("No file found!");
			ex.printStackTrace();
			return -1;
		}catch(ConnectException ex){
			ex.printStackTrace();
		}
		return 1;
	}
}
