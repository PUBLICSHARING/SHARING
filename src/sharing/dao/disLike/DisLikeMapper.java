package sharing.dao.disLike;

import sharing.entity.disLike.DisLike;

public interface DisLikeMapper {

	public Long addDisLike(DisLike disLike) throws Exception;
	
	public DisLike updateDisLike(DisLike disLike) throws Exception;

	public DisLike findDisLikeById(Long disLikeId) throws  Exception;

	public Long deleteDisLikeById(Long disLikeId) throws Exception;

	public String findDisLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception;
	
	public Long deleteDisLikeByDynamicIdAndUserId(Long dynamicId, Long userId) throws Exception;
}
