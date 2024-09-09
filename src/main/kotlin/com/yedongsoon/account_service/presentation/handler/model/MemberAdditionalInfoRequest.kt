package com.yedongsoon.account_service.presentation.handler.model

import com.yedongsoon.account_service.domain.member.model.MemberAdditionalInfoCommand
import java.time.LocalDate


data class MemberAdditionalInfoRequest(
        val name: String?,
        val mobileNo:String,
        val birthDate: LocalDate,
        val profilePhoto: String?,
        //추후 개인정보 동의 여부 추가
){
    fun toCommand(no:Int)= MemberAdditionalInfoCommand(
            memberNo =no,
            name=name,
            mobileNo=mobileNo,
            birthDate=birthDate,
            profilePhoto=profilePhoto,
    )
}