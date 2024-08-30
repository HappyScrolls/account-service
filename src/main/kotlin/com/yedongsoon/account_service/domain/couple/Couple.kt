package com.yedongsoon.account_service.domain.couple

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "couple")
class Couple(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "couple_no")
        val no: Int = 0,

        @Column(name = "account_no_a")
        val accountNoA: Int,

        @Column(name = "account_no_b")
        val accountNoB: Int,

        @Column(name = "couple_name")
        val name: String?,

        @Column(name = "started_at")
        val startedAt: LocalDate?,

        @Column(name = "couple_img")
        val coupleImg: String?,
) {
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

}