package sharing.dao.file.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<SharingFile> findFilesByLimit(Long currentPage, Long pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		Long startIndex = Long.valueOf((currentPage.longValue() - 1L)* pageSize.longValue());
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		return SqlSessionUtil.getSqlSession().selectList("sharing.entity.file.findFilesByLimit",params);
	}

	@Override
	public Long findAllFilesTotal() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.file.findAllFilesTotal");
	}
}
