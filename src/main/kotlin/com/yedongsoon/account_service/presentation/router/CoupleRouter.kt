package com.yedongsoon.account_service.presentation.router

import com.yedongsoon.account_service.presentation.handler.CoupleHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CoupleRouter(private val coupleHandler: CoupleHandler) {
    @Bean
    fun coupleRoute(): RouterFunction<ServerResponse> {
        return coRouter {
            (accept(MediaType.APPLICATION_JSON) and "/couple").nest {
                GET("/detail", coupleHandler::getDetail)
                GET("/lover", coupleHandler::getLover)
            }
        }
    }
}
