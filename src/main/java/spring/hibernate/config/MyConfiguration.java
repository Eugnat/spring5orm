package spring.hibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import spring.hibernate.entities.HibBook;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class MyConfiguration {

    @Bean
    public DataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hibernatedemo?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactoryBean() {

        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();

        sfb.setAnnotatedClasses(HibBook.class);

        Properties properties = new Properties();

       properties.setProperty("hibernate.hbm2ddl.auto","validate");
       properties.setProperty("hibernate.show_sql","true");
       properties.setProperty("hibernate.format_sql","false");

        sfb.setHibernateProperties(properties);
        sfb.setDataSource(getDataSource());

        return sfb;
    }

}
