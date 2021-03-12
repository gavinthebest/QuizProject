package com.gavin.util;

import com.gavin.domain.Question;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

public class QuestionExcelRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(RowSet rs) throws Exception {
        Question question = new Question();

        question.setType(rs.getColumnValue(0));
        question.setDescription(rs.getColumnValue(1));
        String [] temp = rs.getColumnValue(2).split("\\|");
        question.setOptionA(temp[0]);
        question.setOptionB(temp[1]);
        question.setOptionC(temp[2]);
        question.setOptionD(temp[3]);
        question.setAnswerIndex(Integer.parseInt(rs.getColumnValue(3)));

        return question;
    }
}
