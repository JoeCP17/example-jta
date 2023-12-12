package com.jta.user.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class User(
    name: String = ""
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "user_name")
    var name: String = name
        protected set

    @Column(name = "create_date")
    val createDate: LocalDateTime = LocalDateTime.now()

}