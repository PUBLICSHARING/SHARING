package sharing.service.file;

import sharing.entity.file.SharingFile;

public interface SharingFileService {
	public SharingFile findSharingFileById(Long sharingFileId) throws Exception;
	
	public Long addSharingFile(SharingFile sharingFile) throws Exception;
}
