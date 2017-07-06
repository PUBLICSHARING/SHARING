package sharing.service.like;

import sharing.entity.like.Like;

public interface LikeService {
	
	public Long addLike(Like like) throws Exception;
	
	public Like updateLike(Like like) throws Exception;
	
	public Like findLikeById(Long likeId) throws Exception;
	
	public Long deleteLikeById(Long likeId) throws Exception;
}
