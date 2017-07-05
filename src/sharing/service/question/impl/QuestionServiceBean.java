package sharing.service.question.impl;

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
	
}
