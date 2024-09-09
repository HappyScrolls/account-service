package com.yedongsoon.account_service.presentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.reactive.CorsWebFilter

@Configuration
class WebFluxConfig {

    @Bean
    fun corsFilter(): CorsWebFilter {
        val corsConfig = CorsConfiguration()
        corsConfig.allowedOrigins = listOf("*")  // 모든 도메인 허용
        corsConfig.allowedMethods = listOf("*")  // 모든 HTTP 메서드 허용
        corsConfig.allowedHeaders = listOf("*")  // 모든 헤더 허용
        corsConfig.allowCredentials = false      // 자격 증명 비허용

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)  // 모든 경로에 대해 CORS 설정 적용

        return CorsWebFilter(source)
    }
}
