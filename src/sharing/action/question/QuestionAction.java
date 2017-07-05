package sharing.action.question;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sharing.entity.question.Question;
import sharing.service.question.QuestionService;
import sharing.service.question.impl.QuestionServiceBean;

public class QuestionAction {
	
	private QuestionService questionService = new QuestionServiceBean();
	
	public Question addQuestion(Question question) throws Exception {
		try{
			question.setSubmitDate(new Date()); 
			return this.questionService.addQuestion(question);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findQuestionsTotal() throws Exception {
		try{
			return this.questionService.findQuestionsTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Question> findQuestionsByLimit(Long currentPage,Long pageSize) throws Exception {
		try{
			return this.questionService.findQuestionsByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findTotalAndQuestionByLimit(Long currentPage,Long pageSize) throws Exception {
		try{
			Map<String, Object> result = new HashMap<String,Object>();
			result.put("total", this.findQuestionsTotal());
			result.put("questions", this.findQuestionsByLimit(currentPage, pageSize));
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int markReaded(Long questionId) throws Exception{
		try{
			return this.questionService.markReaded(questionId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findQuestionInfoById (Long questionId) throws Exception {
		try{
			return this.questionService.findQuestionInfoById(questionId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findNotReadCountOfQuestion() throws Exception {
		try{
			return this.questionService.findNotReadCountOfQuestion();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int deleteQuestionById(Long questionId) throws Exception {
		try{
			return this.questionService.deleteQuestionById(questionId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
