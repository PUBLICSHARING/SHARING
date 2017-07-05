package sharing.dao.question.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.question.QuestionMapper;
import sharing.entity.question.Question;

public class QuestionController implements QuestionMapper{

	@Override
	public Question addQuestion(Question question) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("remark", question.getRemark());
		params.put("submitDate", question.getSubmitDate());
		params.put("userId", question.getUser().getId());
		SqlSessionUtil.getSqlSession().insert("sharing.entity.question.addQuestion", params);
		return question;
	}

	@Override
	public Long findQuestionsTotal() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.question.findQuestionsTotal");
	}

	@Override
	public List<Question> findQuestionsByLimit(Long currentPage, Long pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		Long startIndex = Long.valueOf((currentPage.longValue() - 1L)* pageSize.longValue());
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		return SqlSessionUtil.getSqlSession().selectList("sharing.entity.question.findQuestionsByLimit", params);
	}

	@Override
	public int markReaded(Long questionId) throws Exception {
		return SqlSessionUtil.getSqlSession().update("sharing.entity.question.markReaded", questionId);
	}

	@Override
	public Map<String, Object> findQuestionInfoById(Long questionId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectMap("sharing.entity.question.findQuestionInfoById", questionId,"questionId");
	}

	@Override
	public Long findNotReadCountOfQuestion() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.question.findNotReadCountOfQuestion");
	}

	@Override
	public int deleteQuestionById(Long questionId) throws Exception {
		return SqlSessionUtil.getSqlSession().delete("sharing.entity.question.deleteQuestionById",questionId);
	}
	
}
