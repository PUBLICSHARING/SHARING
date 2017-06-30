package sharing.entity.file;

import java.io.Serializable;
import java.util.Date;

import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;

/**
 * 文件实体 
 * @author AbnerLi
 *
 */
public class SharingFile implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String fileCode;
	
	private String fileType;
	
	private String fileName;
	
	private Long width;
	
	private Long height;
	
	private Date uploadDate;
	
	private User user;
	
	private Dynamic dynamic;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dynamic getDynamic() {
		return dynamic;
	}

	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}
	
}
