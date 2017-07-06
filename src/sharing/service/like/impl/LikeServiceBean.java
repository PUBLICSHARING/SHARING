
package sharing.service.like.impl;

import java.util.Date;

import sharing.dao.like.LikeMapper;
import sharing.dao.like.impl.LikeController;
import sharing.entity.like.Like;
import sharing.service.like.LikeService;

public class LikeServiceBean implements LikeService {
	private LikeMapper likeMapper = new LikeController();

	@Override
	public Long addLike(Like like) throws Exception {
		try {
			like.setClickTime(new Date());
			return this.likeMapper.addLike(like);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Like updateLike(Like like) throws Exception {
		try {
			return this.likeMapper.updateLike(like);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Like findLikeById(Long likeId) throws Exception {
		try {
			return this.likeMapper.findLikeById(likeId);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Long deleteLikeById(Long likeId) throws Exception {
		try {
			return this.likeMapper.deleteLikeById(likeId);
		} catch(Exception e) {
			throw e;
		}
	}
	
	@Override
	public String findLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception {
		try {
			 return this.likeMapper.findLikeByUserIdAndDynamicId(userId, dynamicId);
		} catch(Exception e) {
			throw e;
		}
	}
}
