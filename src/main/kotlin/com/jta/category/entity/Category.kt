package com.jta.category.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "category")
class Category (
    name: String = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "category_name")
    var name: String = name
        protected set

    @Column(name = "create_date")
    val createDate: LocalDateTime = LocalDateTime.now()

}