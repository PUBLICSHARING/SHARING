package sharing.service.comment.impl;

import sharing.dao.comment.CommentMapper;
import sharing.dao.comment.impl.CommentController;
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
	
}
