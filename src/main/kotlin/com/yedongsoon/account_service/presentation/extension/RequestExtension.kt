package com.yedongsoon.account_service.presentation.extension

import com.yedongsoon.account_service.domain.extension.decodeBase64ToDto
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.queryParamOrNull
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ServerRequest.extractMemberCodeHeader(): MemberHeader {
    return headers().header("Member-Code").firstOrNull()
            ?.let {
                it.decodeBase64ToDto<MemberHeader>()
            } ?: throw IllegalArgumentException()
}


fun ServerRequest.localDateQueryParam(parameter: String): LocalDate {
    return queryParamOrNull(parameter)?.let {
        LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    } ?: throw IllegalArgumentException("")
}
