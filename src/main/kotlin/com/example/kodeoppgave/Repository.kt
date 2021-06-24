package com.example.kodeoppgave

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime


@Repository
class Repository(
    @Qualifier("restTemplate") private val restTemplate: RestTemplate,
) {

    fun getAds(fromDate: LocalDateTime, endDate: LocalDateTime, pageNumber: Int) : Ads {
        return try {
            val adsResponse = restTemplate.exchange(
                "/ads?published=[*,$fromDate)&size=1000&page=$pageNumber",
                org.springframework.http.HttpMethod.GET,
                HttpEntity.EMPTY,
                Ads::class.java
            ).body!!
            adsResponse
        } catch (e: Exception) {
            throw IllegalArgumentException("Feil oppsto ved henting av ads: " + e.message)
        }
    }

}