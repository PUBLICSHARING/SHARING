package sharing.service.disLike.impl;

import java.util.Date;

import sharing.dao.disLike.DisLikeMapper;
import sharing.dao.disLike.impl.DisLikeController;
import sharing.entity.disLike.DisLike;

public class DisLikeServiceBean implements DisLikeService {
	DisLikeMapper disLikeMapper = new DisLikeController();
	
	@Override
	public Long addDisLike(DisLike disLike) throws Exception {
		try {
			disLike.setClickTime(new Date());
			return this.disLikeMapper.addDisLike(disLike);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public DisLike updateDisLike(DisLike disLike) throws Exception {
		try {
			return this.disLikeMapper.updateDisLike(disLike);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public DisLike findDisLikeById(Long disLikeId) throws Exception {
		try {
			return this.disLikeMapper.findDisLikeById(disLikeId);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Long deleteDisLikeById(Long disLikeId) throws Exception {
		try {
			return this.disLikeMapper.deleteDisLikeById(disLikeId);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public String findDisLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception {
		try {
			return this.disLikeMapper.findDisLikeByUserIdAndDynamicId(userId, dynamicId);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Long deleteDisLikeByDynamicIdAndUserId(Long dynamicId, Long userId) throws Exception {
		try {
			return this.disLikeMapper.deleteDisLikeByDynamicIdAndUserId(dynamicId, userId);
		} catch(Exception e) {
			throw e;
		}
	}

}
