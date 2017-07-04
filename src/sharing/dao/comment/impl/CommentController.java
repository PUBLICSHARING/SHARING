package sharing.dao.comment.impl;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.comment.CommentMapper;

public class CommentController implements CommentMapper{

	@Override
	public Long findCountOfCommentsByUserId(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.comment.findCountOfCommentsByUserId", userId);
	}

}
