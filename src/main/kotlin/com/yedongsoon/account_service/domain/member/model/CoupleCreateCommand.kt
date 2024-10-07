package com.yedongsoon.account_service.domain.member.model

data class CoupleCreateCommand(
        val inviteCode:String,
        val accountNoB: Int,
)