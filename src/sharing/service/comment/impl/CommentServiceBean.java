package sharing.service.comment.impl;

import java.util.Date;

import sharing.dao.comment.CommentMapper;
import sharing.dao.comment.impl.CommentController;
import sharing.entity.comment.Comment;
import sharing.service.comment.CommentService;

public class CommentServiceBean implements CommentService{

	private CommentMapper commentMapper = new CommentController();
	
	@Override
	public Long findCountOfCommentsByUserId(Long userId) throws Exception {
		try{
			return this.commentMapper.findCountOfCommentsByUserId(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findCountOfCommentsByUserId",e);
		}
	}
	
	@Override
	public Long addComment(Comment comment) throws Exception{
		try {
			comment.setCommentTime(new Date());
			return this.commentMapper.addComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("addComment",e);
		}
	}
	
}
