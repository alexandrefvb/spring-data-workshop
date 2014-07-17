package br.com.tqi.enquete.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("br.com.tqi.enquete")
@EnableTransactionManagement
class PersistenceConfiguration {

    @Bean
    public DataSource dataSource() {
	BasicDataSource ds = new BasicDataSource();
	ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	ds.setUrl("jdbc:derby:enquetedb;create=true");
	return ds;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	vendorAdapter.setGenerateDdl(true);
	vendorAdapter.setShowSql(true);
	vendorAdapter.setDatabase(Database.DERBY);
	LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	factory.setJpaVendorAdapter(vendorAdapter);
	factory.setPackagesToScan("br.com.tqi.enquete");
	factory.setDataSource(dataSource());
	factory.afterPropertiesSet();

	return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
	JpaTransactionManager txManager = new JpaTransactionManager();
	txManager.setEntityManagerFactory(entityManagerFactory());
	return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
	return new HibernateExceptionTranslator();
    }
}