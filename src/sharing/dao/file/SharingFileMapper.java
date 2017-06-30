package sharing.dao.file;

import sharing.entity.file.SharingFile;

public interface SharingFileMapper{

	public SharingFile findSharingFileById(Long sharingFileId) throws Exception;
	
	public Long addSharingFile(SharingFile sharingFile) throws Exception;

}
