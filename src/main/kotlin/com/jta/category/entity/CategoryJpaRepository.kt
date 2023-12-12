package com.jta.category.entity

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository: JpaRepository<Category, Long> {
}