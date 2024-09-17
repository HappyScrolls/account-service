package com.yedongsoon.account_service.application.exception

import org.springframework.http.HttpStatus

class CoupleNotFoundException(message: String) : AbstractException(HttpStatus.NOT_FOUND, message)
class CoupleExistException(message: String) : AbstractException(HttpStatus.BAD_REQUEST, message)

