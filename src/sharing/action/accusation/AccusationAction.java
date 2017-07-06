package sharing.action.accusation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sharing.entity.accusation.Accusation;
import sharing.service.accusation.AccusationService;
import sharing.service.accusation.impl.AccusationServiceBean;

public class AccusationAction {
	
	private AccusationService accusationService = new AccusationServiceBean();
	
	public Accusation addAccusation(Accusation accusation) throws Exception {
		try{
			return this.accusationService.addAccusation(accusation);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int deleteAccusation(Long accusationId) throws Exception{
		try{
			return this.accusationService.deleteAccusation(accusationId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Accusation> findAccusationByLimit(Long currentPage,Long pageSize) throws Exception {
		try{
			return this.accusationService.findAccusationByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findTotalOfAllAccusation() throws Exception {
		try{
			return this.accusationService.findTotalOfAllAccusation();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findTotalAndAccusationByLimit(Long currentPage,Long pageSize)  throws Exception {
		try{
			Map<String, Object> result = new HashMap<String,Object>();
			result.put("total", this.findTotalOfAllAccusation());
			result.put("accusations", this.findAccusationByLimit(currentPage,pageSize));
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int markReaded(Long accusationId) throws Exception{
		try{
			return this.accusationService.markReaded(accusationId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findCountOfNotReaded() throws Exception {
		try{
			return this.accusationService.findCountOfNotReaded();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
