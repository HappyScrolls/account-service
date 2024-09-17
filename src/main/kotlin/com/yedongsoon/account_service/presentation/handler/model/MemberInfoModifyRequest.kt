package com.yedongsoon.account_service.presentation.handler.model

import com.yedongsoon.account_service.domain.member.model.MemberInfoModifyCommand
import java.time.LocalDate

data class MemberInfoModifyRequest(
        val name: String?,
        val email: String?,
        val profilePhoto: String?,
        val birthDate: LocalDate?,
        val mobileNo:String?,
){
    fun toCommand(memberNo:Int)= MemberInfoModifyCommand(
        memberNo=memberNo,
        name=name,
        email=email,
        profilePhoto=profilePhoto,
        birthDate=birthDate,
        mobileNo=mobileNo,
    )
}