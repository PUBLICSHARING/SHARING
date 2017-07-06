define(['app'],function(app) {
	app.factory('LikeService',function($resource) {
		var likeService = $resource('../LikeAction/:action', {});
		
		likeService.addLike = function(like, sucesscb, errorcb) {
			likeService.save({action:"addLike"},{like:like}, sucesscb, errorcb);
		},
		
		likeService.findLikeById = function(likeId,sucesscb,errorcb) {
			likeService.get({action:"findLikeById",likeId:likeId},sucesscb,errorcb);
		},
		
		likeService.updateLike = function(like,sucesscb,errorcb) {
			likeService.save({action:"updateLike"},{like:like},sucesscb,errorcb);
		},
		
		likeService.deleteLikeById = function(likeId,sucesscb,errorcb)	{
			likeService.save({action:"deleteLikeById"},{likeId:likeId},sucesscb,errorcb);
		},
		likeService.findLikeByUserIdAndDynamicId = function(userId, dynamicId, sucesscb, errorcb) {
			likeService.get({action:"findLikeByUserIdAndDynamicId",userId:userId, dynamicId:dynamicId},sucesscb,errorcb);
		},
		likeService.deleteLikeByDynamicIdAndUserId = function(dynamicId, userId, sucesscb, errorcb)	{
			likeService.save({action:"deleteLikeByDynamicIdAndUserId"},{dynamicId:dynamicId, userId:userId},sucesscb,errorcb);
		}
		return likeService;
	})
})