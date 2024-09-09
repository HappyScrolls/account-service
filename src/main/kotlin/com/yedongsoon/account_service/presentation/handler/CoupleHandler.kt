package com.yedongsoon.account_service.presentation.handler

import com.yedongsoon.account_service.application.couple.CoupleQueryService
import com.yedongsoon.account_service.presentation.extension.extractMemberCodeHeader
import com.yedongsoon.account_service.presentation.handler.model.CoupleResponse
import com.yedongsoon.account_service.presentation.handler.model.MemberResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service
class CoupleHandler(
        private val coupleQueryService: CoupleQueryService,
) {
    suspend fun getDetail(request: ServerRequest): ServerResponse = withContext(Dispatchers.IO) {
        val memberHeader = request.extractMemberCodeHeader()

        val result = coupleQueryService.getDetail(memberHeader.no)
        ServerResponse.ok().bodyValueAndAwait(CoupleResponse.from(result))
    }

    suspend fun getLover(request: ServerRequest): ServerResponse = withContext(Dispatchers.IO) {
        val memberHeader = request.extractMemberCodeHeader()

        val result = coupleQueryService.getLover(memberHeader.no)
        ServerResponse.ok().bodyValueAndAwait(MemberResponse.from(result))
    }
}
