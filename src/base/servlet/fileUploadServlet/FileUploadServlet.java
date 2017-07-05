package base.servlet.fileUploadServlet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sharing.action.dynamic.DynamicAction;
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
	
	private static DynamicAction dynamicAction = new DynamicAction();
	
	private static Long dynamicId;
	
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
		
		
		List<SharingFile> files = new ArrayList<SharingFile>();
		try {  
			@SuppressWarnings("unchecked")  
			Map<String,List<FileItem>> list = upload.parseParameterMap(request);  
			List<FileItem> dd = list.get("file_data");
			List<FileItem> cc = list.get("dynamic");
			
			//文本信息，动态的文本类内容
			for(FileItem item : cc){
				if(item.isFormField()){//判断是否是文件流 ，这里还有一种是文本
					 String name = item.getFieldName();  
			         String value = item.getString("utf-8");
			         String [] mes = value.split(",");
			         
			         //用户信息
			         User user = new User();
			         user.setId(Long.parseLong(mes[0]));
			         int mark = Integer.parseInt(mes[2]);
			         if(mark==3){
			        	 Dynamic dynamic = new Dynamic();
			        	 dynamic.setContent(mes[1]);
			        	 dynamic.setUser(user);
			        	 
			        	 //设置点赞人数
			        	 dynamic.setDislikeNum((long) 0);
			        	 dynamic.setLikeNum((long)0);
			        	 
			        	 dynamic.setPublishTime(new Date());
			        	 synchronized (dynamic) {
			        		 dynamicId = dynamicAction.addDynamic(dynamic);
						}
			         }
				}
			}
			
			//附加文件动态
			for(FileItem item : dd){  
				if(dd == null)
					break;
				if(item.isFormField()){//判断是否是文件流 ，这里还有一种是文本
					
				}else{  
					//会将完整路径名传过来 
					String value = item.getName(); 
					int start = value.lastIndexOf("\\");  
					String fileName = value.substring(start+1); 
					
					//处理图片
					InputStream in = handUploadImg(item);
					//以流的形式返回上传文件的主体内容
					//InputStream in = item.getInputStream();  
					
					int index = fileName.lastIndexOf(".");  
					String realFileName = fileName.substring(0,index);  
					String type = fileName.substring(index+1);
					
					//图片的流处理为base64编码
					String base64Code = ioToBase64(in);
					
					//构造一个文件的对象
					SharingFile sharingFile = new SharingFile();
					Dynamic dynamic = new Dynamic();
					Long id = dynamicAction.findMaxDynamicId();
					dynamic.setId(id);
					
					sharingFile.setFileName(realFileName);
					sharingFile.setFileCode(base64Code);
					sharingFile.setFileType(type);
					sharingFile.setUploadDate(new Date());
					sharingFile.setDynamic(dynamic);
					
					//持久化到数据库中
					sharingFileAction.addSharingFile(sharingFile);
				}  
				out.write(JSON.toJSONString(new Object()));
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
	
	//处理上传的图片大小
	public InputStream handUploadImg(FileItem item){
		try {
			InputStream inputStream = item.getInputStream();
			//设置图片高度为620px
			int imgHeight = 720;
			int imgWidth = 450;
			
			//获取图片
			Image src = ImageIO.read(inputStream); 
			
			//将设置好的图片追加到BufferedImage中 
			BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB); 
			Graphics2D graphics = bufferedImage.createGraphics(); 
			
			//重构图片 
			graphics.drawImage(src, 0, 0, imgWidth, imgHeight, null); 
			
			ByteArrayOutputStream bs =new ByteArrayOutputStream(); 
			
			ImageOutputStream imOut =ImageIO.createImageOutputStream(bs); 
			
			ImageIO.write(bufferedImage,"jpg",imOut); 
			
			//将图片转为inputStream流 
			inputStream = new ByteArrayInputStream(bs.toByteArray());
			
			return inputStream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}