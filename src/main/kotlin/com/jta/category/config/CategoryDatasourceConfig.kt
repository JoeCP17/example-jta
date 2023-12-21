package com.jta.category.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.jta.category.entity"],
    entityManagerFactoryRef = "categoryEntityManagerFactory",
    transactionManagerRef = "categoryTransactionManager"
)
class CategoryDatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.test3")
    fun categoryDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .build()
    }

    @Bean
    fun categoryEntityManagerFactory(
        categoryDataSource: DataSource,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(categoryDataSource)
            .packages("com.jta.category.entity")
            .persistenceUnit("test3")
            .build()
    }

    @Bean
    fun categoryTransactionManager(
        categoryEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(categoryEntityManagerFactory.getObject()!!)
    }

}