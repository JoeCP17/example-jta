package com.jta.shop.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "shop")
class Shop (
    name: String = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "shop_name")
    var name: String = name
        protected set

    @Column(name = "create_date")
    val createDate: LocalDateTime = LocalDateTime.now()

}