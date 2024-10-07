package com.yedongsoon.account_service.presentation.handler.model

import com.yedongsoon.account_service.domain.member.model.CoupleCreateCommand


data class CoupleCreateRequest(
        val inviteCode: String,
){
    fun toCommand(memberNo:Int)=CoupleCreateCommand(
            inviteCode = inviteCode,
            accountNoB = memberNo,
    )
}