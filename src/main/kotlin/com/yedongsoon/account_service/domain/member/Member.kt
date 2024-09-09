package com.yedongsoon.account_service.domain.member

import com.yedongsoon.account_service.domain.member.model.MemberAdditionalInfoCommand
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
@Entity
@Table(name = "member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    val no: Int = 0,

    @Column(name = "account_id")
    val accountId: String?,

    name: String?,

    @Column(name = "email")
    val email: String?,

    profilePhoto: String?,

    birthDate: LocalDate?,

    mobileNo:String?,
) {
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "name")
    var name: String?=name
        private set
    @Column(name = "profile_photo")
    var profilePhoto: String?=profilePhoto
        private set
    @Column(name = "birth_date")
    var birthDate: LocalDate?=birthDate
        private set
    @Column(name = "mobile_no")
    var mobileNo: String?=mobileNo
        private set
    fun createAdditionalInfo(command:MemberAdditionalInfoCommand){
        name= command.name?:name
        profilePhoto=command.profilePhoto
        birthDate=command.birthDate
        mobileNo=command.mobileNo
    }
}