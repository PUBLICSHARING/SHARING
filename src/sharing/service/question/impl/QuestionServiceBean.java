package sharing.service.question.impl;

import java.util.List;
import java.util.Map;

import sharing.dao.question.QuestionMapper;
import sharing.dao.question.impl.QuestionController;
import sharing.entity.question.Question;
import sharing.service.question.QuestionService;

public class QuestionServiceBean implements QuestionService{

	private QuestionMapper questionMapper = new QuestionController();
	
	@Override
	public Question addQuestion(Question question) throws Exception {
		try{
			return this.questionMapper.addQuestion(question);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("addQuestion",e);
		}
	}

	@Override
	public Long findQuestionsTotal() throws Exception {
		try{
			return this.questionMapper.findQuestionsTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findQuestionsTotal",e);
		}
	}

	@Override
	public List<Question> findQuestionsByLimit(Long currentPage,Long pageSize) throws Exception {
		try{
			return this.questionMapper.findQuestionsByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findQuestionsByLimit",e);
		}
	}

	@Override
	public int markReaded(Long questionId) throws Exception {
		try{
			return this.questionMapper.markReaded(questionId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("markReaded",e);
		}
	}

	@Override
	public Map<String, Object> findQuestionInfoById(Long questionId) throws Exception {
		try{
			return this.questionMapper.findQuestionInfoById(questionId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findQuestionInfoById",e);
		}
	}

	@Override
	public Long findNotReadCountOfQuestion() throws Exception {
		try{
			return this.questionMapper.findNotReadCountOfQuestion();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findNotReadCountOfQuestion",e);
		}
	}

	@Override
	public int deleteQuestionById(Long questionId) throws Exception {
		try{
			return this.questionMapper.deleteQuestionById(questionId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("deleteQuestionById",e);
		}
	}
}
