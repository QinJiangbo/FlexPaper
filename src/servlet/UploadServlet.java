package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

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
	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//instance a factory class
		DiskFileItemFactory difactory = new DiskFileItemFactory();
		
		//set the buffer size
		difactory.setSizeThreshold(1000*1024*1024);
		
		//set the temporary directory
		String rootDir = this.getServletContext().getRealPath("/");
		System.out.println(rootDir);
		
		File tempDir = new File(rootDir+"\\temp");
		if(!tempDir.exists()){
			tempDir.mkdir();
		}
		difactory.setRepository(tempDir);
		
		//set the file storing directory
		File uploadDir = new File(rootDir+"\\upload");
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		
		//create the parser of request
		ServletFileUpload sfu = new ServletFileUpload(difactory);
		
		List list = null;
		try{
			list = sfu.parseRequest(request);
		}catch(FileUploadException ex){
			ex.printStackTrace();
		}
		
		String[] array = new String[4];
		//traverse the list and get values
		for(int i = list.size()-1; i >= 0; i--){
			FileItem fi = (FileItem) list.get(i);
			if(fi.isFormField()){
				System.out.println(fi.getFieldName()+"\t");
				System.out.println(fi.getString());
				array[i-1] = fi.getString();
			}else{
				//store the file
				String filename = fi.getName();
				int index = filename.lastIndexOf("\\");
				if(index!=-1){
					filename = filename.substring(index+1);
				}
				
				//write the file to the server
				InputStream in = fi.getInputStream();
				
				//fi.delete(); //delete the file in the temporary folder
				
				//sort the files based on their catalogs and author names
				String fileDir = uploadDir+"\\"+array[1]+"\\"+array[0]+"\\"+array[2]+"\\"+array[3];
				File files = new File(fileDir);
				
				if(!files.exists()){
					files.mkdirs();
				}
				
				FileOutputStream fos = new FileOutputStream(fileDir+"\\"+filename);
				String file_path = fileDir+"\\"+filename;
				System.out.println(file_path);
				request.setAttribute("file_path", file_path);
				byte[] buf = new byte[1024];
				int len = -1;
				while((len = in.read(buf))!=-1){
					fos.write(buf,0,len);
				}
				if(in!=null){
					try{
						in.close();
					}finally{
						if(fos!=null){
							fos.close();
						}
					}
				}
			}
		}
		request.setAttribute("msg","<script>alert('Upload Successfully!')</script>");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
}
