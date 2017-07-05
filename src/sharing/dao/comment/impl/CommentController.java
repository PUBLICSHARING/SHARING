package sharing.dao.comment.impl;

import sharing.dao.comment.CommentMapper;
import sharing.entity.comment.Comment;
import base.sqlSession.SqlSessionUtil;

public class CommentController implements CommentMapper{

	@Override
	public Long findCountOfCommentsByUserId(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.comment.findCountOfCommentsByUserId", userId);
	}
	
	@Override
	public Long addComment(Comment comment) throws Exception{
		SqlSessionUtil.getSqlSession().insert("sharing.entity.comment.addComment", comment);
		return comment.getId();
	}

}
