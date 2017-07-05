package sharing.service.question;

import java.util.List;
import java.util.Map;

import sharing.entity.question.Question;

public interface QuestionService {

	public Question addQuestion(Question question) throws Exception;

	public Long findQuestionsTotal() throws Exception;

	public List<Question> findQuestionsByLimit(Long currentPage, Long pageSize) throws Exception;

	public int markReaded(Long questionId) throws Exception;

	public Map<String, Object> findQuestionInfoById(Long questionId) throws Exception;

	public Long findNotReadCountOfQuestion() throws Exception;

	public int deleteQuestionById(Long questionId) throws Exception;

}
