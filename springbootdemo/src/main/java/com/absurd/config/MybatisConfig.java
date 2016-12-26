package com.absurd.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by Administrator on 2016/5/20.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
@MapperScan("com.absurd.mapper")
public class MybatisConfig {
    private static final String MAPPER_SCAN_BASE_PACKAGE = "com.absurd.mapper";


//    @Primary //默认数据源
//    @Bean(initMethod = "init", destroyMethod = "close")
//    @ConfigurationProperties(prefix = "spring.druid")
//    public DataSource dataSource() {
//        return new DruidDataSource();
//    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionBean = new SqlSessionFactoryBean();
        sqlSessionBean.setDataSource(dataSource);
        sqlSessionBean.setTypeAliasesPackage("com.absurd.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionBean.setMapperLocations(resolver
                .getResources("classpath*:com/absurd/mapper/*Mapper.xml"));
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        pageHelper.setProperties(props);

        Interceptor[] interceptors = new Interceptor[]{pageHelper};
        sqlSessionBean.setPlugins(interceptors);
        return sqlSessionBean.getObject();
    }


    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
        final MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage(MAPPER_SCAN_BASE_PACKAGE);
        msc.afterPropertiesSet();
        msc.setAnnotationClass(org.springframework.stereotype.Repository.class);
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        msc.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        return msc;
    }



}
