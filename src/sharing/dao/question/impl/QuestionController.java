package sharing.dao.question.impl;

import java.util.HashMap;
import java.util.Map;

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
	
}
