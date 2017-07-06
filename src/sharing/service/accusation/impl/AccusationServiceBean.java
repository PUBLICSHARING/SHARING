package sharing.service.accusation.impl;

import java.util.List;

import sharing.dao.accusation.AccusationMapper;
import sharing.dao.accusation.impl.AccusationController;
import sharing.entity.accusation.Accusation;
import sharing.service.accusation.AccusationService;

public class AccusationServiceBean implements AccusationService{

	private AccusationMapper accusationMapper = new AccusationController();
	
	@Override
	public Accusation addAccusation(Accusation accusation) throws Exception {
		try{
			return this.accusationMapper.addAccusation(accusation);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("addAccusation",e);
		}
	}

	@Override
	public int deleteAccusation(Long accusationId) throws Exception {
		try{
			return this.accusationMapper.deleteAccusation(accusationId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("deleteAccusation",e);
		}
	}

	@Override
	public List<Accusation> findAccusationByLimit(Long currentPage, Long pageSize) throws Exception {
		try{
			return this.accusationMapper.findAccusationByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findAccusationByLimit",e);
		}
	}

	@Override
	public Long findTotalOfAllAccusation() throws Exception {
		try{
			return this.accusationMapper.findTotalOfAllAccusation();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findTotalOfAllAccusation",e);
		}
	}

	@Override
	public int markReaded(Long accusationId) throws Exception {
		try{
			return this.accusationMapper.markReaded(accusationId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("markReaded",e);
		}
	}

	@Override
	public Long findCountOfNotReaded() throws Exception {
		try{
			return this.accusationMapper.findCountOfNotReaded();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findCountOfNotReaded",e);
		}
	}

}
