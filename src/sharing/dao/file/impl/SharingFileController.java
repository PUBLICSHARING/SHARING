package sharing.dao.file.impl;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.file.SharingFileMapper;
import sharing.entity.file.SharingFile;

public class SharingFileController implements SharingFileMapper{

	@Override
	public SharingFile findSharingFileById(Long sharingFileId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.file.SharingFile", sharingFileId);
	}

	@Override
	public Long addSharingFile(SharingFile sharingFile) throws Exception {
		SqlSessionUtil.getSqlSession().insert("sharing.entity.file.addSharingFile", sharingFile);
		return sharingFile.getId();
	}
}
