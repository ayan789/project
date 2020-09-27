package com.ccic.salessapp.common.mybatis.autoconfigure;
import com.ccic.salessapp.common.mybatis.MybatisProperties;
import com.ccic.salessapp.common.mybatis.SpringBootVFS;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@AutoConfigureAfter(PlatformDataSourceConfig.class)
public class PlatformMyBatisConfig {

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    /**
     * 自己構造SqlSessionFactory
     * @return
     * @throws Exception
     * @See  org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
     */
    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(MybatisProperties properties) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        factory.setConfiguration(new org.apache.ibatis.session.Configuration());
        if (!ObjectUtils.isEmpty(properties.resolveMapperLocations())) {
            factory.setMapperLocations(properties.resolveMapperLocations());
        }
        addPageInterceptor(factory);
        return factory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    private void addPageInterceptor(SqlSessionFactoryBean factory) {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("params", "count=countSql");
        properties.setProperty("autoDialect", "true");
        properties.setProperty("autoRuntimeDialect", "true");
        properties.setProperty("closeConn", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        interceptor.setProperties(properties);
        factory.setPlugins(interceptor);
    }
}
