package sharing.dao.comment;

public interface CommentMapper {

	public Long findCountOfCommentsByUserId(Long userId) throws Exception;

}
