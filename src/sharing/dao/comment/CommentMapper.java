package sharing.dao.comment;

import sharing.entity.comment.Comment;

public interface CommentMapper {

	public Long findCountOfCommentsByUserId(Long userId) throws Exception;

	public Long addComment(Comment comment) throws Exception;

}
