package com.example.kodeoppgave

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

data class Ads(@JsonProperty("content") val content: List<Ad>,
               @JsonProperty("totalElements") val totalElements: Int,
               @JsonProperty("pageNumber") val pageNumber: Int,
               @JsonProperty("pageSize") val pageSize: Int,
               @JsonProperty("totalPages") val totalPages: Int,
               @JsonProperty("first") val first: Boolean,
               @JsonProperty("last") val last: Boolean,
               @JsonProperty("sort") val sort: String)

data class Ad(@JsonProperty("uuid") val uuid: String,
              @JsonProperty("published") val published: String,
              @JsonProperty("updated") val updated: String,
              @JsonProperty("expires") val expires: String,
              @JsonProperty("title") val title: String,
              @JsonProperty("description") val description: String)

val weekfields: WeekFields = WeekFields.of(Locale.getDefault())
var fromDate: LocalDateTime = LocalDateTime.now()
val endDate: LocalDateTime = fromDate.minusMonths(6)

fun toLocalDateTime(date: String): LocalDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)

fun List<Ad>.filterOlderAds(endDate: LocalDateTime): List<Ad>{
    return this.filter { toLocalDateTime(it.published) > endDate }
}

fun List<Ad>.getAdsWithKeyword(keyword: String): List<Ad>{
    return this.filter { it.description.toLowerCase().contains(keyword) }
}

fun List<Ad>.groupPerWeek(): Map<Int, Int>{
    return this.groupingBy { toLocalDateTime(it.published).get(weekfields.weekOfYear()) }.eachCount()
}