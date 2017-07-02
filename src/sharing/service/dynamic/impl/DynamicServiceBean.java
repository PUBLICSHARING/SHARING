package sharing.service.dynamic.impl;

import java.util.Date;

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

}
