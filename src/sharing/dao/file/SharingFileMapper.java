package sharing.dao.file;

import java.util.List;

import sharing.entity.file.SharingFile;

public interface SharingFileMapper{

	public SharingFile findSharingFileById(Long sharingFileId) throws Exception;
	
	public Long addSharingFile(SharingFile sharingFile) throws Exception;

	public List<SharingFile> findFilesByLimit(Long currentPage, Long pageSize) throws Exception;

	public Long findAllFilesTotal() throws Exception;

}
