package sharing.action.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sharing.entity.file.SharingFile;
import sharing.service.file.SharingFileService;
import sharing.service.file.impl.SharingFileServiceBean;

public class SharingFileAction {
	private SharingFileService sharingFileService = new SharingFileServiceBean();
	
	public Long addSharingFile(SharingFile sharingFile) throws Exception{
		try {
			return this.sharingFileService.addSharingFile(sharingFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findAllFilesTotal() throws Exception {
		try{
			return this.sharingFileService.findAllFilesTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<SharingFile> findFilesByLimit(Long currentPage,Long pageSize) throws Exception{
		try{
			return this.sharingFileService.findFilesByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findFilesAndTotalByLimit(Long currentPage,Long pageSize) throws Exception{
		try{
			Map<String, Object> result = new HashMap<String,Object>();
			result.put("total", this.findAllFilesTotal());
			result.put("files", this.findFilesByLimit(currentPage, pageSize));
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
