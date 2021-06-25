package kodeoppgaveTest

import com.example.kodeoppgave.KodeoppgaveApplication
import org.springframework.boot.runApplication

class KodeoppgaveApplicationTest: KodeoppgaveApplication()

fun main(args: Array<String>) {
    runApplication<KodeoppgaveApplicationTest>(*args)
}
