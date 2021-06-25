package kodeoppgaveTest

import com.example.kodeoppgave.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class KodeoppgaveTest {
    @Test
    fun correctKeywordsAreFound() {
        assertTrue(testAds.content.getAdsWithKeyword("java").size == 1)
        assertTrue(testAds.content.getAdsWithKeyword("kotlin").size == 1)
    }

    @Test
    fun otherAdDoesNotContainKeywords() {
        assertTrue(listOf<Ad>(otherAd).getAdsWithKeyword("java").isEmpty())
        assertTrue(listOf<Ad>(otherAd).getAdsWithKeyword("kotlin").isEmpty())
    }

    @Test
    fun absentKeywordIsEmpty() {
        assertTrue(testAds.content.getAdsWithKeyword("nonExistentString").isEmpty())
    }

    @Test
    fun removeOlderAds() {
        val cutOffDate = toLocalDateTime("2021-06-25T22:00:00Z")
        assert(testAds.content.none { toLocalDateTime(it.published) > cutOffDate })
        assert(testAds.content.filterOlderAds(cutOffDate).isEmpty())
    }

    @Test
    fun groupAdsPerWeek() {
        val adsWithJavaPerWeek = testAds.content
            .getAdsWithKeyword("java")
            .groupPerWeek()
        val adsWithKotlinPerWeek = testAds.content
            .getAdsWithKeyword("kotlin")
            .groupPerWeek()
        val adsWithoutKeywords = testAds.content
            .getAdsWithKeyword("nonExistentString")
            .groupPerWeek()

        assertTrue(adsWithJavaPerWeek.containsKey(24))
        assertTrue(adsWithJavaPerWeek.containsValue(1))
        assertTrue(adsWithKotlinPerWeek.containsKey(24))
        assertTrue(adsWithKotlinPerWeek.containsValue(1))
        assertTrue(adsWithoutKeywords.isEmpty())
        assertTrue(adsWithoutKeywords.isEmpty())
    }
}
