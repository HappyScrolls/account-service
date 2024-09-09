package com.yedongsoon.account_service.presentation.handler

import com.yedongsoon.account_service.application.member.MemberCommandService
import com.yedongsoon.account_service.presentation.extension.extractMemberCodeHeader
import com.yedongsoon.account_service.presentation.handler.model.CoupleResponse
import com.yedongsoon.account_service.presentation.handler.model.MemberAdditionalInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service
class MemberHandler(
        private val memberCommandService: MemberCommandService,
) {
    suspend fun createAdditional(request: ServerRequest): ServerResponse = withContext(Dispatchers.IO) {
        val memberHeader = request.extractMemberCodeHeader()
        val command = request.awaitBodyOrNull<MemberAdditionalInfoRequest>()?.toCommand(memberHeader.no)
                ?: throw IllegalArgumentException()

        memberCommandService.createAdditional(command)
        ServerResponse.ok().buildAndAwait()
    }
}