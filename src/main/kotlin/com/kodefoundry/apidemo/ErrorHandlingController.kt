package com.kodefoundry.apidemo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@ControllerAdvice
class ErrorHandlingController
{
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VTSNotFoundException::class)
    fun notFoundHandler() : ErrorResponse {
        return ErrorResponse(404, "Vehicle Testing Station not found")
    }
}
