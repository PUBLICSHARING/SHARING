package sharing.dao.dynamic;

import java.util.List;
import java.util.Map;

import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;

public interface DynamicMapper {
	public Long addDynamic(Dynamic dynamic) throws Exception;

	public Long findAllDynamicsTotal() throws Exception;

	public List<Dynamic> findDynamicsByLimit(Long currentPage, Long pageSize) throws Exception;

	public Long findCountOfDynamicsByUserId(Long userId) throws Exception;
	
	public Long findMaxDynamicId() throws Exception;
	
	public List<Dynamic> findAllDynamicsByUserId(Long id, Long currentPage, Long pageSize) throws Exception;

	public List<Dynamic> findNewestDynamics(Long currentPage, Long pageSize) throws Exception;
	
	public Dynamic updateDynamic(Dynamic dynamic) throws Exception;
}
