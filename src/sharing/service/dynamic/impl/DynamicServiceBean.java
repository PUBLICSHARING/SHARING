package sharing.service.dynamic.impl;

import java.util.Date;
import java.util.List;

import sharing.dao.dynamic.DynamicMapper;
import sharing.dao.dynamic.impl.DynamicController;
import sharing.entity.dynamic.Dynamic;
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

}
