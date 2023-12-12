package com.jta.shop.entity

import org.springframework.data.jpa.repository.JpaRepository

interface ShopJpaRepository: JpaRepository<Shop, Long> {
}