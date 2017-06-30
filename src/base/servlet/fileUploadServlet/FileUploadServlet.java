package base.servlet.fileUploadServlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import sharing.action.file.SharingFileAction;
import sharing.entity.dynamic.Dynamic;
import sharing.entity.file.SharingFile;
import sharing.entity.user.User;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;

@WebServlet(description = "上传图片servlet", urlPatterns = { "/PlUploadServlet" })
public class FileUploadServlet extends HttpServlet {
	//图片上传与下载的action层
	private static SharingFileAction sharingFileAction = new SharingFileAction();
	
	public FileUploadServlet() {
		super();
	}

	/** 
	 * 处理用户上传请求 
	 */  
	private static final long serialVersionUID = 1L;  

	public void doGet(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  
		this.doPost(request, response);
	}  

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  
		
		PrintWriter out = response.getWriter();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();  
		
		@SuppressWarnings("deprecation")  
		String path = request.getRealPath("/upload");//设置磁盘缓冲路径  

		factory.setRepository(new File(path));  
		factory.setSizeThreshold(1024*1024);//设置创建缓冲大小  

		ServletFileUpload upload = new ServletFileUpload(factory);  
		
		upload.setSizeMax(-1);//设置上传文件限制大小,-1无上限  
		try {  
			@SuppressWarnings("unchecked")  
			Map<String,List<FileItem>> list = upload.parseParameterMap(request);  
			List<FileItem> dd = list.get("file_data");
			String va = null;  
			for(FileItem item : dd){  
				//String name = item.getFieldName();  
				if(item.isFormField()){//判断是否是文件流 ，这里还有一种是文本
					va = item.getString("UTF-8");  
					//  System.out.println(name+"="+va);  
					/// request.setAttribute(name, value);  
				}else{  
					//会将完整路径名传过来 
					String value = item.getName(); 
					int start = value.lastIndexOf("\\");  
					String fileName = value.substring(start+1);  
					//以流的形式返回上传文件的主体内容
					InputStream in = item.getInputStream();  
					//item.write(new File(realPath,fileName));  
					int index = fileName.lastIndexOf(".");  
					String realFileName = fileName.substring(0,index);  
					String type = fileName.substring(index+1);
					
					//图片的流处理为base64编码
					String base64Code = ioToBase64(in);
					
					//构造一个文件的对象
					SharingFile sharingFile = new SharingFile();
					sharingFile.setFileName(realFileName);
					sharingFile.setFileCode(base64Code);
					sharingFile.setFileType(type);
					sharingFile.setUploadDate(new Date());
					
					sharingFileAction.addSharingFile(sharingFile);
					
					//write方法将FileItem对象中的内容保存到某个指定的文件中
					//item.write(new File("D:\\12324.jpg"));
					
					//base64ToIo(base64Code);
				}  
				Object object = new Object();
				String dString = "test";
				out.write(JSON.toJSONString(dString));
			} 
		} catch (Exception e) {  

			e.printStackTrace();  
		}  

	}  
	
	public String ioToBase64(InputStream in) throws IOException {
		String strBase64 = null;
		try {
			// in.available()返回文件的字节长度
			byte[] bytes = new byte[in.available()];
			// 将文件中的内容读入到数组中
			in.read(bytes);
			strBase64 = new BASE64Encoder().encode(bytes);      //将字节流数组转换为字符串
			in.close();
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return strBase64;
	}

	public void base64ToIo(String strBase64) throws IOException {
		String string = strBase64;
		String fileName = "d:/gril2.jpg"; //生成的新文件
		try {
			// 解码，然后将字节转换为文件
			byte[] bytes = new BASE64Decoder().decodeBuffer(string);   //将字符串转换为byte数组
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			byte[] buffer = new byte[1024];
			FileOutputStream out = new FileOutputStream(fileName);
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = in.read(buffer)) != -1) {
				bytesum += byteread;
				out.write(buffer, 0, byteread); //文件写操作
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}