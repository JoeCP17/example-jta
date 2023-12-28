package com.jta.config

import jakarta.transaction.TransactionManager
import jakarta.transaction.UserTransaction
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform
import org.springframework.stereotype.Component

@Component
class AtomikosJtaPlatformConfig(

): AbstractJtaPlatform() {

    private val serialVersionUID = 1L
    override fun locateTransactionManager(): TransactionManager {
        TODO("Not yet implemented")
    }

    override fun locateUserTransaction(): UserTransaction {
        TODO("Not yet implemented")
    }
}