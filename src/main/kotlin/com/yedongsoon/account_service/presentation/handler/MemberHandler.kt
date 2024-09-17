package com.yedongsoon.account_service.presentation.handler

import com.yedongsoon.account_service.application.member.MemberCommandService
import com.yedongsoon.account_service.application.member.MemberQueryService
import com.yedongsoon.account_service.presentation.extension.extractMemberCodeHeader
import com.yedongsoon.account_service.presentation.handler.model.CoupleResponse
import com.yedongsoon.account_service.presentation.handler.model.MemberAdditionalInfoRequest
import com.yedongsoon.account_service.presentation.handler.model.MemberInfoModifyRequest
import com.yedongsoon.account_service.presentation.handler.model.MemberResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service
class MemberHandler(
        private val memberCommandService: MemberCommandService,
        private val memberQueryService: MemberQueryService,
) {
    suspend fun createAdditional(request: ServerRequest): ServerResponse = withContext(Dispatchers.IO) {
        val memberHeader = request.extractMemberCodeHeader()
        val command = request.awaitBodyOrNull<MemberAdditionalInfoRequest>()?.toCommand(memberHeader.no)
                ?: throw IllegalArgumentException()

        memberCommandService.createAdditional(command)
        ServerResponse.ok().buildAndAwait()
    }
    suspend fun getMember(request: ServerRequest): ServerResponse = withContext(Dispatchers.IO) {
        val memberHeader = request.extractMemberCodeHeader()
        val result=memberQueryService.getMember(memberHeader.no)
        ServerResponse.ok().bodyValueAndAwait(MemberResponse.from(result))
    }

    suspend fun modifyMemberInfo(request: ServerRequest): ServerResponse = withContext(Dispatchers.IO) {
        val memberHeader = request.extractMemberCodeHeader()
        val command = request.awaitBodyOrNull<MemberInfoModifyRequest>()?.toCommand(memberHeader.no)
                ?: throw IllegalArgumentException()

        memberCommandService.modifyMemberInfo(command)
        ServerResponse.ok().buildAndAwait()
    }
}