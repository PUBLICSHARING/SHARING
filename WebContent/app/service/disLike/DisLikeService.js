define(['app'],function(app) {
	app.factory('DisLikeService',function($resource) {
		var disLikeService = $resource('../DisLikeAction/:action', {});
		
		disLikeService.addDisLike = function(disLike, sucesscb, errorcb) {
			disLikeService.save({action:"addDisLike"},{disLike:disLike}, sucesscb, errorcb);
		},
		
		disLikeService.findDisLikeById = function(disLikeId,sucesscb,errorcb) {
			disLikeService.get({action:"findDisLikeById",disLikeId:disLikeId},sucesscb,errorcb);
		},
		
		disLikeService.updateDisLike = function(disLike,sucesscb,errorcb) {
			disLikeService.save({action:"updateDisLike"},{disLike:disLike},sucesscb,errorcb);
		},
		
		disLikeService.deleteDisLikeById = function(disLikeId,sucesscb,errorcb)	{
			disLikeService.save({action:"deleteDisLikeById"},{disLikeId:disLikeId},sucesscb,errorcb);
		},
		disLikeService.findDisLikeByUserIdAndDynamicId = function(userId, dynamicId, sucesscb, errorcb) {
			disLikeService.get({action:"findDisLikeByUserIdAndDynamicId",userId:userId, dynamicId:dynamicId},sucesscb,errorcb);
		}
		return likeService;
	})
})