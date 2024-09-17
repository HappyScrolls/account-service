package com.yedongsoon.account_service.presentation.handler.model

import com.yedongsoon.account_service.domain.member.model.CoupleInfoCreateCommand
import jakarta.persistence.Column
import java.time.LocalDate

data class CoupleInfoCreateRequest(
        val name: String,
        val nickNameA: String,
        val nickNameB: String,
        val startedAt: LocalDate,
        val coupleImg: String?,
){
    fun toCommand(memberNo:Int) =CoupleInfoCreateCommand(
            memberNo=memberNo,
            name=name,
            nickNameA=nickNameA,
            nickNameB=nickNameB,
            startedAt=startedAt,
            coupleImg=coupleImg,
    )
}