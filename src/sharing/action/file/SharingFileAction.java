package sharing.action.file;

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
}
