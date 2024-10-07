package com.yedongsoon.account_service.application.couple

import com.yedongsoon.account_service.application.exception.CoupleExistException
import com.yedongsoon.account_service.application.exception.CoupleNotFoundException
import com.yedongsoon.account_service.domain.couple.Couple
import com.yedongsoon.account_service.domain.couple.CoupleRepository
import com.yedongsoon.account_service.domain.member.MemberRepository
import com.yedongsoon.account_service.domain.member.model.CoupleCreateCommand
import com.yedongsoon.account_service.domain.member.model.CoupleInfoCreateCommand
import com.yedongsoon.account_service.domain.member.model.CoupleInfoModifyCommand
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service

@Service
class CoupleCommandService(
        private val coupleRepository: CoupleRepository,
) {
    fun createCoupleInfo(command:CoupleInfoCreateCommand) {
        coupleRepository.findByAccountNoAOrAccountNoB(command.memberNo, command.memberNo)?.let {
            it.createInfo(command)
            coupleRepository.save(it)
        } ?: throw CoupleNotFoundException("커플 정보가 존재하지 않습니다")
    }

    fun createCouple(command: CoupleCreateCommand) {
        coupleRepository.findByAccountNoAOrAccountNoB(command.accountNoA, command.accountNoB)?.let {
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
}