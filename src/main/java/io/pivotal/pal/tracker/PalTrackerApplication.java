package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {

    @Bean
    TimeEntryRepository timeEntryRepository(DataSource mysqlDataSource) {
        return new JdbcTimeEntryRepository(mysqlDataSource);
    }


    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }
}