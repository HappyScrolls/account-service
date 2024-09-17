package com.yedongsoon.account_service.domain.couple

import com.yedongsoon.account_service.domain.member.model.CoupleCreateCommand
import com.yedongsoon.account_service.domain.member.model.CoupleInfoCreateCommand
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

) {
    @Column(name = "couple_name")
    var name: String?=null
        private set

    @Column(name = "nick_name_a")
    var nickNameA: String?=null
        private set
    @Column(name = "nick_name_b")
    var nickNameB: String?=null
        private set
    @Column(name = "started_at")
    var startedAt: LocalDate?=null
        private set
    @Column(name = "couple_img")
    var coupleImg: String?=null
        private set
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

    fun createInfo(command: CoupleInfoCreateCommand) {
        name=command.name
        nickNameA=command.nickNameA
        nickNameB=command.nickNameB
        startedAt=command.startedAt
        coupleImg=command.coupleImg
    }

    companion object {
        fun create(command: CoupleCreateCommand)=Couple(
                accountNoA = command.accountNoA,
                accountNoB = command.accountNoB
        )
    }

}