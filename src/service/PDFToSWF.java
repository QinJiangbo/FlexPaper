package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PDFToSWF {

	public static void main(String[] args) throws IOException {
		String PDF = "C:/Users/Richard/Desktop/Hello.pdf";
		String SWF = "C:/Users/Richard/Desktop/Hello.swf";
		String command = "G:/Development/SWFTools/pdf2swf.exe " + PDF + " -o "
				+ SWF + " -f -T 9 ";
		System.out.println(command);
		Process pro = Runtime.getRuntime().exec(command);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(pro.getInputStream()));
		while (bufferedReader.readLine() != null) {
			try {
				pro.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void convert(String PDF) throws IOException{
		String SWF = PDF;
		String command = "G:/Development/SWFTools/pdf2swf.exe " + PDF + " -o "
				+ SWF + " -f -T 9 ";
		System.out.println(command);
		Process pro = Runtime.getRuntime().exec(command);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(pro.getInputStream()));
		while (bufferedReader.readLine() != null) {
			try {
				pro.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
