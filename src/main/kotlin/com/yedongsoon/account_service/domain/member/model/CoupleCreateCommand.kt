package com.yedongsoon.account_service.domain.member.model

data class CoupleCreateCommand(
        val accountNoA: Int,
        val accountNoB: Int,
)