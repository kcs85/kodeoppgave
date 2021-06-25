package com.example.kodeoppgave

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class Service(
    val repository: Repository
) {

    fun getAds() {
        val ads = getAdsPerDateSegment()

        val adsWithJavaPerWeek = ads
            .getAdsWithKeyword("java")
            .groupPerWeek()
        val adsWithKotlinPerWeek = ads
            .getAdsWithKeyword("kotlin")
            .groupPerWeek()

        prettyPrintResults(adsWithJavaPerWeek, adsWithKotlinPerWeek)
    }

    fun prettyPrintResults(adsWithJavaPerWeek: Map<Int,Int>, adsWithKotlinPerWeek: Map<Int,Int>) {
        val mapper = ObjectMapper()
        val jsonJava = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(adsWithJavaPerWeek)
        val jsonKotlin = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(adsWithKotlinPerWeek)
        println("Java : week and number of ads" )
        println(jsonJava)
        println("Kotlin : week and number of ads" )
        println(jsonKotlin)
    }

    fun getAdsPerDateSegment(): List<Ad>{
        println("Fetching ads from $fromDate to $endDate")

        val totalAds = mutableListOf<List<Ad>>()
        while(fromDate > endDate){
            println("Starting fetching from published date: $fromDate")
            val adsFromFeed= getPages(fromDate, endDate)
            totalAds.add(adsFromFeed)
            fromDate = toLocalDateTime(adsFromFeed.last().published)
        }

        return totalAds.flatten().filterOlderAds(endDate)
    }

    fun getPages(fromPublishedDate: LocalDateTime, endDate: LocalDateTime): List<Ad>{
        var pageNumber = 0
        var isLast = false

        val ads = mutableListOf<List<Ad>>()
        while(!isLast){
            val response = repository.getAds(fromPublishedDate, endDate, pageNumber)
            ads.add(response.content)
            pageNumber++
            isLast = response.last || toLocalDateTime(response.content.last().published) < endDate
        }

        return ads.flatten()
    }

}

