package service;

import java.io.File;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class OfficeToPDF {

	public static void main(String[] args) throws ConnectException {
//		File input = new File("C:/Users/Richard/Desktop/Hello.docx");
//		File output = new File("C:/Users/Richard/Desktop/Hello.pdf");
//		OpenOfficeConnection connection = new SocketOpenOfficeConnection(
//                "127.0.0.1", 8100);
//		connection.connect();
//		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
//		converter.convert(input, output);
//		connection.disconnect();
		convert("C:\\Users\\Richard\\Desktop\\Test.docx");
	}
	
	public static void convert(String inputFile) throws ConnectException{
		File input = new File(inputFile);
		int index = inputFile.lastIndexOf("\\");
		String path = inputFile.substring(0, index);
		String name = inputFile.substring(index+1);
		String outputFile = path + "\\" + name.substring(0, name.lastIndexOf(".")) + ".pdf";
		System.out.println(outputFile);
		File output = new File(outputFile);
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                "127.0.0.1", 8100);
		connection.connect();
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(input, output);
		connection.disconnect();
	}
}
