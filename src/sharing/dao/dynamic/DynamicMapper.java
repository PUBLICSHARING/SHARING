package sharing.dao.dynamic;

import java.util.List;

import sharing.entity.dynamic.Dynamic;

public interface DynamicMapper {
	public Long addDynamic(Dynamic dynamic) throws Exception;

	public Long findAllDynamicsTotal() throws Exception;

	public List<Dynamic> findDynamicsByLimit(Long currentPage, Long pageSize) throws Exception;
}
