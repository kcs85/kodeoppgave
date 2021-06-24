package com.example.kodeoppgave

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@ResponseStatus(value = HttpStatus.OK)

class Controller(
    private val service: Service,
) {
    @GetMapping(value = ["/getads"], produces = ["application/json"])
    fun getAds() {
        service.getAds()
    }

}