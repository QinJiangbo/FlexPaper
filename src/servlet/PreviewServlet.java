package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class PreviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filePath = request.getParameter("path");
		int index = filePath.lastIndexOf("\\");
		String name = filePath.substring(index+1);
		String ext = name.substring(name.lastIndexOf(".")+1);
		String nameWithoutExt;
		if(ext.equals("pdf")){
			nameWithoutExt = pdf2swf(filePath);
		}else{
			nameWithoutExt = pdf2swf(office2pdf(filePath));
		}
		request.setAttribute("name", nameWithoutExt);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	public String office2pdf(String inputFile) throws ConnectException{
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
		return outputFile;
	}
	
	public String pdf2swf(String PDF) throws IOException{
		String rootDir = this.getServletContext().getRealPath("/");
		int index = PDF.lastIndexOf("\\");
		String name = PDF.substring(index+1);
		String nameWithoutExt = name.substring(0, name.lastIndexOf("."));
		String SWF = rootDir + "\\swf" + "\\" + nameWithoutExt + ".swf";
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
		
		return nameWithoutExt;
	}
}
