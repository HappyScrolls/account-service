package com.yedongsoon.account_service.domain.member.model

import java.time.LocalDate

data class CoupleInfoModifyCommand(
        val name: String?,
        val nickNameA: String?,
        val nickNameB: String?,
        val startedAt: LocalDate?,
        val coupleImg: String?,
)