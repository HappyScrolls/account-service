package com.yedongsoon.account_service.presentation.handler.model

import com.yedongsoon.account_service.domain.member.model.CoupleCreateCommand


data class CoupleCreateRequest(
        val requestAccountNo: Int,
){
    fun toCommand(memberNo:Int)=CoupleCreateCommand(
            accountNoA = requestAccountNo,
            accountNoB = memberNo,
    )
}