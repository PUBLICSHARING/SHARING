package sharing.service.disLike.impl;

import sharing.entity.disLike.DisLike;

public interface DisLikeService {

	public Long addDisLike(DisLike disLike) throws Exception;
	
	public DisLike updateDisLike(DisLike disLike) throws Exception;
	
	public DisLike findDisLikeById(Long disLikeId) throws  Exception;
	
	public Long deleteDisLikeById(Long disLikeId) throws Exception;
	
	public String findDisLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception;
}
