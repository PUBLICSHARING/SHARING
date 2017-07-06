package sharing.action.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sharing.entity.dynamic.Dynamic;
import sharing.service.dynamic.DynamicService;
import sharing.service.dynamic.impl.DynamicServiceBean;

public class DynamicAction {
	private DynamicService dynamicService = new DynamicServiceBean();
	
	
	/**
	 * 添加动态
	 */
	public Long addDynamic(Dynamic dynamic) throws Exception{
		try {
			return this.dynamicService.addDynamic(dynamic);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 动态总数
	 */
	public Long findAllDynamicsTotal() throws Exception {
		try{
			return this.dynamicService.findAllDynamicsTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 分页查询动态
	 */
	public List<Dynamic> findDynamicsByLimit(Long currentPage,Long pageSize) throws Exception{
		try{
			return this.dynamicService.findDynamicsByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 分页查询动态及总数
	 */
	public Map<String, Object> findDynamicsAndTotalByLimit(Long currentPage,Long pageSize) throws Exception{
		try{
			Map<String, Object> result = new HashMap<String,Object>();
			result.put("total",this.findAllDynamicsTotal());
			result.put("dynamics", this.findDynamicsByLimit(currentPage, pageSize));
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findCountOfDynamicsByUserId(Long userId) throws Exception {
		try{
			return this.dynamicService.findCountOfDynamicsByUserId(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findMaxDynamicId() throws Exception{
		try {
			return this.dynamicService.findMaxDynamicId();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Dynamic> findAllDynamicsByUserId(Long id) throws Exception{
		try {
			return this.dynamicService.findAllDynamicsByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Dynamic> findNewestDynamics() throws Exception{
		try {
			return this.dynamicService.findNewestDynamics();
/*			Set<Entry<String, Object>> setIterator = newestDynamic.entrySet();
			
			Iterator<Entry<String, Object>> iterator = setIterator.iterator();
			
			//进行头像的处理，顺便带出头像
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				Dynamic dynamic = (Dynamic)entry.getValue();
				User user = dynamic.getUser();
				String userHeadImg = this.uerService.findUserHeadImg(user.getId());
				user.setHeadImg(userHeadImg);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Dynamic updateDynamic(Dynamic dynamic) throws Exception {
		try {
			return this.dynamicService.updateDynamic(dynamic);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

