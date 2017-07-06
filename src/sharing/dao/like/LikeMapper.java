package sharing.dao.like;

import sharing.entity.like.Like;

public interface LikeMapper {

	public Long addLike(Like like) throws Exception;
	
	public Like updateLike(Like like) throws Exception;
	
	public Like findLikeById(Long likeId) throws Exception;
	
	public Long deleteLikeById(Long likeId) throws Exception;
	
	public String findLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception;
	
	public Long deleteLikeByDynamicIdAndUserId(Long dynamicId, Long userId) throws Exception;
}
