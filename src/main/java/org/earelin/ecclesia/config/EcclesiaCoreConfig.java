package org.earelin.ecclesia.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.tika.Tika;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.service.resource.FileServiceImpl;
import org.earelin.ecclesia.service.resource.FileService;
import org.hibernate.SessionFactory;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordValidator;
import org.passay.WhitespaceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main configuration class
 * 
 * @author Xavier Carriba
 * @since 0.1
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
    
    @Value("${server.url}")
    private String serverUrl;
    
    @Value("${private.files.folder}")
    private String privateFilesFolder;
    
    @Value("${public.files.folder}")
    private String publicFilesFolder;
    
    @Value("${public.files.url}")
    private String publicFilesURL;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
    
    @Bean
    public Tika tika() {
        return new Tika();
    }
    
    @Bean
    public PasswordValidator passwordValidator() {
	return new PasswordValidator(Arrays.asList(
	    new LengthRule(7, 32),
	    new CharacterRule(EnglishCharacterData.Alphabetical, 1),
	    new CharacterRule(EnglishCharacterData.Digit, 1),
	    new WhitespaceRule())
	);
    }
    
    @Bean
    public FileService fileService(Tika tika) throws IOException {
        return new FileServiceImpl(privateFilesFolder, publicFilesFolder,
                publicFilesURL, serverUrl, tika);
    }
    
    @Bean  
    public ResourceBundleMessageSource messageSource() {  
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();  
        source.setBasename("locale/messages");  
        source.setUseCodeAsDefaultMessage(true);
	source.setDefaultEncoding("UTF-8");
        return source;  
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
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
    
}
