package sharing.action.question;

import java.util.Date;
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
}
