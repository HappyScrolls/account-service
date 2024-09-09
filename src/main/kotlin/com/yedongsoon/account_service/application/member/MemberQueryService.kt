package com.yedongsoon.account_service.application.member

import com.yedongsoon.account_service.application.exception.MemberNotFoundException
import com.yedongsoon.account_service.domain.member.Member
import com.yedongsoon.account_service.domain.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberQueryService(
        private val memberRepository: MemberRepository,
) {
    suspend fun getMember(memberNo: Int) : Member {
       return memberRepository.findByIdOrNull(memberNo)?:throw throw MemberNotFoundException("유저를 찾을 수 없습니다")
    }
}