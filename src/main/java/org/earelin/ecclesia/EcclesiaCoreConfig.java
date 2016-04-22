package org.earelin.ecclesia;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main configuration class
 */
@Configuration
@ImportResource("classpath:/spring-security-config.xml")
@EnableTransactionManagement
@EnableAsync
@PropertySource("classpath:/default.properties")
@PropertySource(value = "file:${ECCLESIA_CONFIG_FILE}", ignoreResourceNotFound = true)
@ComponentScan(basePackages="org.earelin.ecclesia.service,org.earelin.ecclesia.repository,org.earelin.ecclesia.security")
public class EcclesiaCoreConfig {
    
    @Value("${jdbc.driver}")
    private String jdbcDriverClassName;
    
    @Value("${jdbc.url}")
    private String jdbcUrl;
    
    @Value("${jdbc.username}")
    private String jdbcUsername;
    
    @Value("${jdbc.password}")
    private String jdbcPassword;
    
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateAuto;
    
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    
    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;
    
    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }
    
    @Bean 
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] {"org.earelin.ecclesia.domain"});
        sessionFactory.setHibernateProperties(new Properties() {
            {
                setProperty("hibernate.dialect", hibernateDialect);
                setProperty("hibernate.hbm2ddl.auto", hibernateAuto);
                setProperty("hibernate.show_sql", hibernateShowSql);
                setProperty("hibernate.format_sql", hibernateFormatSql);
            }
        });
        return sessionFactory;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
}
