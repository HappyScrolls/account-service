package com.yedongsoon.account_service.domain.member

import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<Member, Int> {
    fun findByNo(no:Int):Member?
}