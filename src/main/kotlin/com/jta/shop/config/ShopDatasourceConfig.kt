package com.jta.shop.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.jta.shop.entity"],
    entityManagerFactoryRef = "shopEntityManagerFactory",
    transactionManagerRef = "shopTransactionManager"
)
class ShopDatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    fun shopDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .build()
    }

    @Bean
    fun shopEntityManagerFactory(
        shopDataSource: DataSource,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(shopDataSource)
            .packages("com.jta.shop.entity")
            .persistenceUnit("test2")
            .build()
    }

    @Bean
    fun shopTransactionManager(
        shopEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(shopEntityManagerFactory.getObject()!!)
    }

}