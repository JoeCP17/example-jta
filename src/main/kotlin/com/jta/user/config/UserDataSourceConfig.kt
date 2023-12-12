package com.jta.user.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.jta.user.entity"],
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userTransactionManager"
)
class UserDataSourceConfig {

    /**
     * @description : 다중 Datasource 설정 시, 대표 DataSource를 선언해야 하기에, Primary를 통한 대표 설정 ( 다른 방법은 없을까? )
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    fun userDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .build()
    }

    @Primary
    @Bean
    fun userEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        userDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(userDataSource)
            .packages("com.jta.user.entity")
            .persistenceUnit("test1")
            .build()
    }

    @Primary
    @Bean
    fun userTransactionManager(
        userEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(userEntityManagerFactory.getObject()!!)
    }

}