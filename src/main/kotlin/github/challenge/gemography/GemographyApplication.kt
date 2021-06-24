package github.challenge.gemography

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GemographyApplication

fun main(args: Array<String>) {
	runApplication<GemographyApplication>(*args)
}
