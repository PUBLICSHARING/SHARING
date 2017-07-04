package sharing.service.file;

import java.util.List;

import sharing.entity.file.SharingFile;

public interface SharingFileService {
	public SharingFile findSharingFileById(Long sharingFileId) throws Exception;
	
	public Long addSharingFile(SharingFile sharingFile) throws Exception;

	public Long findAllFilesTotal() throws Exception;

	public List<SharingFile> findFilesByLimit(Long currentPage, Long pageSize) throws Exception;
}
