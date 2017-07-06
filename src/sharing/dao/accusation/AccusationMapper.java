package sharing.dao.accusation;

import java.util.List;

import sharing.entity.accusation.Accusation;

public interface AccusationMapper {

	Accusation addAccusation(Accusation accusation) throws Exception;

	int deleteAccusation(Long accusationId) throws Exception;

	List<Accusation> findAccusationByLimit(Long currentPage, Long pageSize) throws Exception;

	Long findTotalOfAllAccusation() throws Exception;

	int markReaded(Long accusationId) throws Exception;

	Long findCountOfNotReaded() throws Exception;

}
