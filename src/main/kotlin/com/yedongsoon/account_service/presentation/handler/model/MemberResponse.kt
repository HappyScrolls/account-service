package com.yedongsoon.account_service.presentation.handler.model

import com.yedongsoon.account_service.domain.couple.Couple
import com.yedongsoon.account_service.domain.member.Member
import java.time.LocalDate

data class MemberResponse(
        val no: Int = 0,
        val accountId: String?,
        val name: String?,
        val email: String?,
        val profilePhoto: String?,
){
    companion object{
        fun from(member: Member)=MemberResponse(
                no = member.no,
                accountId = member.accountId,
                name = member.name,
                email = member.email,
                profilePhoto = member.profilePhoto,
        )
    }
}