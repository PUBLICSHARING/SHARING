package sharing.action.disLike;

import sharing.entity.disLike.DisLike;
import sharing.service.disLike.impl.DisLikeService;
import sharing.service.disLike.impl.DisLikeServiceBean;

public class DisLikeAction {
	DisLikeService disLikeService = new DisLikeServiceBean();
	
	public Long addDisLike(DisLike disLike) throws Exception {
		try {
			return this.addDisLike(disLike);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public DisLike updateDisLike(DisLike disLike) throws Exception {
		try {
			return this.disLikeService.updateDisLike(disLike);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public DisLike findDisLikeById(Long disLikeId) throws  Exception {
		try {
			return this.disLikeService.findDisLikeById(disLikeId);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long deleteDisLikeById(Long disLikeId) throws Exception {
		try {
			return this.disLikeService.deleteDisLikeById(disLikeId);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/*方法,根据用户ID和动态ID查询是否踩过该条动态*/
	public String findDisLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception {
		try {
			return this.disLikeService.findDisLikeByUserIdAndDynamicId(userId, dynamicId);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
