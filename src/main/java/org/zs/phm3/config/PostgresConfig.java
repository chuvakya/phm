package org.zs.phm3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@Profile("default")
@PropertySource({ "classpath:application.yaml" })
@EnableJpaRepositories(
        basePackages = {"org.zs.phm3.auditlog", "org.zs.phm3.dashboard", "org.zs.phm3.config",
                "org.zs.phm3.data", "org.zs.phm3.dto", "org.zs.phm3.exception", "org.zs.phm3.failure",
                "org.zs.phm3.ftamodel", "org.zs.phm3.install", "org.zs.phm3.models", "org.zs.phm3.repository",
                "org.zs.phm3.scheduled", "org.zs.phm3.service", "org.zs.phm3.util", "org.zs.phm3.validation",
                "org.zs.phm3.fds"},
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {

    @Value(value = "${hibernate.postgres.hbm2ddl.auto}")
    private String dllAuto;

    @Value(value = "${hibernate.postgres.show_sql}")
    private String showSql;

    @Value(value = "${hibernate.postgres.dialect}")
    private String dialect;


    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.postgres")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "postgresEntityManager")
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("postgres");
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "org.zs.phm3.auditlog", "org.zs.phm3.dashboard", "org.zs.phm3.config",
        "org.zs.phm3.data", "org.zs.phm3.dto", "org.zs.phm3.exception", "org.zs.phm3.failure",
        "org.zs.phm3.ftamodel", "org.zs.phm3.install", "org.zs.phm3.models", "org.zs.phm3.repository",
        "org.zs.phm3.scheduled", "org.zs.phm3.service", "org.zs.phm3.util", "org.zs.phm3.validation", "org.zs.phm3.fds"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", dllAuto);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        properties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return transactionManager;
    }
}
