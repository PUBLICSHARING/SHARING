package sharing.service.dynamic.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import sharing.dao.dynamic.DynamicMapper;
import sharing.dao.dynamic.impl.DynamicController;
import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;
import sharing.service.dynamic.DynamicService;

public class DynamicServiceBean implements DynamicService{

	private DynamicMapper dynamicMapper = new DynamicController();

	@Override
	public Long addDynamic(Dynamic dynamic) throws Exception {
		try {
			dynamic.setPublishTime(new Date());
			return this.dynamicMapper.addDynamic(dynamic);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("addDynamic",e);
		}
	}

	@Override
	public Long findAllDynamicsTotal() throws Exception {
		try{
			return this.dynamicMapper.findAllDynamicsTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findAllDynamicsTotal",e);
		}
	}

	@Override
	public List<Dynamic> findDynamicsByLimit(Long currentPage, Long pageSize) throws Exception {
		try{
			return this.dynamicMapper.findDynamicsByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findDynamicsByLimit",e);
		}
	}

	@Override
	public Long findCountOfDynamicsByUserId(Long userId) throws Exception {
		try{
			return this.dynamicMapper.findCountOfDynamicsByUserId(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findCountOfDynamicsByUserId",e);
		}
	}
	
	@Override
	public Long findMaxDynamicId() throws Exception{
		try {
			return this.dynamicMapper.findMaxDynamicId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("findMaxDynamicId",e);
		}
	}

	@Override
	public List<Dynamic> findAllDynamicsByUserId(Long id) throws Exception {
		try {
			return this.dynamicMapper.findAllDynamicsByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("findAllDynamicsByUserId",e);
		}
	}
	
	@Override
	public List<Dynamic> findNewestDynamics() throws Exception{
		try {
			return this.dynamicMapper.findNewestDynamics();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("findAllDynamicsByUserId",e);
		}
	}
	
	@Override
	public Dynamic updateDynamic(Dynamic dynamic) throws Exception {
		try {
			return this.dynamicMapper.updateDynamic(dynamic);
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("updateDynamic", e);
		}
	}

}
