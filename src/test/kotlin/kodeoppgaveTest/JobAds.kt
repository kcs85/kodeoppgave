package kodeoppgaveTest

import com.example.kodeoppgave.Ad
import com.example.kodeoppgave.Ads


val javaAd = Ad(
    uuid= "8f0c0a87-6e29-46d6-b583-0eadf012eb5b",
    published= "2021-06-20T06:52:28.878908Z",
    expires= "2021-07-15T22:00:00Z",
    updated= "2021-06-27T06:52:28.947004Z",
    title= "Javautvikler i midlertidig stilling",
    description= "<p>Vi leter etter en java assistent i 100% stilling frem til jul som kan java. Vi ser etter en blid og positiv person i et kort engasjement. Du må være glad i barn, ønske å jobbe i et team og være fleksibel."
)

val kotlinAd = Ad(
    uuid= "8f0c0a87-6e29-46d6-b583-0eadf012eb53",
    published= "2021-06-20T06:52:28.878908Z",
    expires= "2021-07-15T22:00:00Z",
    updated= "2021-06-27T06:52:28.947004Z",
    title= "Kotlinuvikler i midlertidig stilling",
    description= "<p>Vi leter etter en assistent i 100% stilling frem til jul som kan kotlin. Vi ser etter en blid og positiv person i et kort engasjement. Du må være glad i barn, ønske å jobbe i et team og være fleksibel."
)

val otherAd = Ad(
    uuid= "8f0c0a87-6e29-46d6-b583-0eadf012eb53",
    published= "2021-06-22T06:52:28.878908Z",
    expires= "2021-07-15T22:00:00Z",
    updated= "2021-06-27T06:52:28.947004Z",
    title= "Assistent i midlertidig stilling",
    description= "<p>Vi leter etter en assistent i 100% stilling frem til jul som er flink. Vi ser etter en blid og positiv person i et kort engasjement. Du må være glad i barn, ønske å jobbe i et team og være fleksibel."
)

val testAds = Ads(
    content= listOf<Ad>(javaAd, kotlinAd, otherAd),
    totalElements=3,
    pageNumber=0,
    pageSize= 100,
    totalPages= 1,
    first= true,
    last= true,
    sort= "published:desc"
)