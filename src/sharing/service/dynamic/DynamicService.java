package sharing.service.dynamic;

import java.util.List;
import java.util.Map;

import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;

public interface DynamicService {
	public Long addDynamic(Dynamic dynamic) throws Exception;

	public Long findAllDynamicsTotal() throws Exception;

	public List<Dynamic> findDynamicsByLimit(Long currentPage, Long pageSize) throws Exception;

	public Long findCountOfDynamicsByUserId(Long userId) throws Exception;

	public Long findMaxDynamicId() throws Exception;
	
	public Map<String, Object> findAllDynamicsByUserId(Long id) throws Exception;

	public Map<String, Object> findNewestDynamics() throws Exception;
	
}
