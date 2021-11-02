package practice.ssm.warehouse;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import practice.ssm.warehouse.mapper.ExampleMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan( basePackageClasses = {RootConfig.class},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "learn.gdut.graduate.warehouse.controller.*")
    }
)
public class RootConfig {

    @Bean
    public DataSource dataSource(@Value("${jdbc.url}") String url,
                                 @Value("${jdbc.username}") String username,
                                 @Value("${jdbc.password}") String password){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        config.addDataSourceProperty("connectionTimeout", "1000");
        config.addDataSourceProperty("idleTimeout", "60000");
        config.addDataSourceProperty("maximumPollSize", "10");

        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    /**
     * mybatis-spring
     * @param dataSource
     * @param resourceLoader
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(resourceLoader.getResource("classpath:/mybatis/config.xml"));
        return factoryBean.getObject();
    }

    /**
     * 开启 Spring 事物管理器。
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public ExampleMapper exampleMapper(SqlSession sqlSession){
        //sqlSessionFactory.getConfiguration().addMapper(ExampleMapper.class);//java配置sql，需要注册sql,故需要注册class。
        return sqlSession.getMapper(ExampleMapper.class);
    }
}
