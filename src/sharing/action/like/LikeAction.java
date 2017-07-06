package sharing.action.like;

import sharing.entity.like.Like;
import sharing.service.like.LikeService;
import sharing.service.like.impl.LikeServiceBean;

public class LikeAction {
	LikeService likeService = new LikeServiceBean();
	
	public Long addLike(Like like) throws Exception {
		try {
			return this.likeService.addLike(like);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Like updateLike(Like like) throws Exception {
		try {
			return this.likeService.updateLike(like);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Like findLikeById(Long likeId) throws Exception {
		try {
			return this.likeService.findLikeById(likeId);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long deleteLikeById(Long likeId) throws Exception {
		try {
			return this.likeService.deleteLikeById(likeId);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/*方法,根据用户ID和动态ID查询是否赞过该条动态*/
	public String findLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception {
		try {
			return this.likeService.findLikeByUserIdAndDynamicId(userId, dynamicId);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
