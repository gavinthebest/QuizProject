package com.gavin.util;
import com.gavin.dao.QuestionDAO;
import com.gavin.domain.Question;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class ExcelFileToDatabaseJobConfig {

    @Bean
    ItemReader<Question> excelQuestionReader() {
        PoiItemReader<Question> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("data/questions.xlsx"));
        reader.setRowMapper(excelRowMapper());
        return reader;
    }

    private RowMapper<Question> excelRowMapper() {
        return new QuestionExcelRowMapper();
    }
}