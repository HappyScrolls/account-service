package com.yedongsoon.account_service.application.couple

import com.yedongsoon.account_service.application.exception.CoupleExistException
import com.yedongsoon.account_service.application.exception.CoupleNotFoundException
import com.yedongsoon.account_service.application.exception.InvalidInviteCodeException
import com.yedongsoon.account_service.domain.couple.Couple
import com.yedongsoon.account_service.domain.couple.CoupleRepository
import com.yedongsoon.account_service.domain.extension.encodeDtoToBase64
import com.yedongsoon.account_service.domain.member.MemberRepository
import com.yedongsoon.account_service.domain.member.model.CoupleCreateCommand
import com.yedongsoon.account_service.domain.member.model.CoupleInfoCreateCommand
import com.yedongsoon.account_service.domain.member.model.CoupleInfoModifyCommand
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CoupleCommandService(
        private val coupleRepository: CoupleRepository,
        private val redisTemplate: ReactiveRedisTemplate<String, String>
) {
    fun createCoupleInfo(command:CoupleInfoCreateCommand) {
        coupleRepository.findByAccountNoAOrAccountNoB(command.memberNo, command.memberNo)?.let {
            it.createInfo(command)
            coupleRepository.save(it)
        } ?: throw CoupleNotFoundException("커플 정보가 존재하지 않습니다")
    }

    suspend fun createCouple(command: CoupleCreateCommand) {
        val memberNo = redisTemplate.opsForValue().getAndAwait(command.inviteCode) ?: throw InvalidInviteCodeException("유효하지 않은 초대코드입니다.")
        coupleRepository.findByAccountNoAOrAccountNoB(memberNo.toInt(), command.accountNoB)?.let {
            throw CoupleExistException("이미 커플이 존재하는 회원입니다.")
        }
        coupleRepository.save(Couple.create(command))
    }

    fun modifyCoupleInfo(memberNo: Int, command: CoupleInfoModifyCommand) {
        coupleRepository.findByAccountNoAOrAccountNoB(memberNo, memberNo)?.let{
            it.modify(command)
            coupleRepository.save(it)
        } ?: throw CoupleExistException("이미 커플이 존재하는 회원입니다.")


    }

    suspend fun createInviteCode(memberNo: Int):String {
        val code=memberNo.toString()+LocalDateTime.now()
        redisTemplate.opsForValue().setAndAwait(code.encodeDtoToBase64(),memberNo.toString(), Duration.ofMinutes(5))
        return code.encodeDtoToBase64()
    }
}