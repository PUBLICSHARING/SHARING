package sharing.action.comment;

import sharing.entity.comment.Comment;
import sharing.service.comment.CommentService;
import sharing.service.comment.impl.CommentServiceBean;

public class CommentAction {
	
	private CommentService commentService = new CommentServiceBean();
	
	public Long findCountOfCommentsByUserId(Long userId) throws Exception{
		try{
			return commentService.findCountOfCommentsByUserId(userId);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long addComment(Comment comment)throws Exception{
		try {
			return commentService.addComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
