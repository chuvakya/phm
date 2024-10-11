//package org.zs.phm3.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@PropertySource({ "classpath:application.yaml" })
//@EnableJpaRepositories(
//        basePackages = "org.zs.phm3.gjb",
//        entityManagerFactoryRef = "mysqlEntityManager",
//        transactionManagerRef = "mysqlTransactionManager"
//)
//public class MySQLConfig {
//
//    @Value(value = "${hibernate.mysql.hbm2ddl.auto}")
//    private String dllAuto;
//
//    @Value(value = "${hibernate.mysql.show_sql}")
//    private String showSql;
//
//    @Value(value = "${hibernate.mysql.dialect}")
//    private String dialect;
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.mysql")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "mysqlEntityManager")
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setPersistenceUnitName("mysql");
//        em.setDataSource(mysqlDataSource());
//        em.setPackagesToScan(new String[] { "org.zs.phm3.gjb" });
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", dllAuto);
//        properties.put("hibernate.show_sql", showSql);
//        properties.put("hibernate.dialect", dialect);
//        em.setJpaPropertyMap(properties);
//        return em;
//    }
//
//    @Bean(name = "mysqlTransactionManager")
//    public PlatformTransactionManager mysqlTransactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
//        return transactionManager;
//    }
//
//}