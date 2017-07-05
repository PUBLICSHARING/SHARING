package sharing.service.comment;

import sharing.entity.comment.Comment;

public interface CommentService {

	public Long findCountOfCommentsByUserId(Long userId) throws Exception;

	public Long addComment(Comment comment) throws Exception;

}
