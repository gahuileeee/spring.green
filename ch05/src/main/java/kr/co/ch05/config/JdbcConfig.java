package kr.co.ch05.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean //return 객체를 bean에 등록
    public DataSource dataSource(){
        //connection pool임
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/studydb");
        dataSource.setUsername("abc1234");
        dataSource.setPassword("Password1!");

        // DBCP2 옵션 설정
        dataSource.setMaxTotal(13); //최대 연결 풀 크기 설정
        dataSource.setMaxIdle(13); // 최대 유효 연결 풀 크기 설정
        return dataSource;
    }
    
    @Bean //이친구는 isnert, select하는 녀석임
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    
}
