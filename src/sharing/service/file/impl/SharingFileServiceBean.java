package sharing.service.file.impl;

import java.util.List;

import sharing.dao.file.SharingFileMapper;
import sharing.dao.file.impl.SharingFileController;
import sharing.entity.file.SharingFile;
import sharing.service.file.SharingFileService;

public class SharingFileServiceBean implements SharingFileService{
	
	private SharingFileMapper sharingFileMapper = new SharingFileController();
	
	@Override
	public SharingFile findSharingFileById(Long sharingFileId) throws Exception{
		try {
			return sharingFileMapper.findSharingFileById(sharingFileId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("findSharingFileById",e);
		}
	}

	@Override
	public Long addSharingFile(SharingFile sharingFile) throws Exception{
		try {
			return sharingFileMapper.addSharingFile(sharingFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("addSharingFile",e);
		}
	}

	@Override
	public Long findAllFilesTotal() throws Exception {
		try{
			return this.sharingFileMapper.findAllFilesTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findAllFilesTotal",e);
		}
	}

	@Override
	public List<SharingFile> findFilesByLimit(Long currentPage, Long pageSize) throws Exception {
		try{
			return this.sharingFileMapper.findFilesByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findFilesByLimit",e);
		}
	}

}
