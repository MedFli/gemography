package github.challenge.gemography.service

import github.challenge.gemography.model.ListRepos
import github.challenge.gemography.model.Repos
import github.challenge.gemography.util.loggerFor
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.streams.toList


@Service
class ReposService(
    private val restTemplate: RestTemplate
) {
    private val logger = loggerFor(this::class.java)

    val BASE_URL = "https://api.github.com/search/repositories?q=created&sort=star&order=desc"

    fun importRepositoriesJson(language: String): List<Repos>? {
        val response = restTemplate.exchange(
            BASE_URL,
            HttpMethod.GET,
            null,
            ListRepos::class.java
        ).also { logger.info("importing api rest data done successfully ") }
        val repositories = response.body?.items
        if (repositories.isNullOrEmpty()) {
            logger.error("something goes wrong , can't import data rest")
        }
        val listReturned = repositories?.stream()?.filter { it ->
            it.languageUsed.equals(language)
        }?.toList().also { logger.info("filtering repositories using $language done successfully") }

        val counter = listReturned?.stream()?.count()

        return listReturned.also {
            logger.info("fetching data completed , this language was used by $counter Repositories")
        }
    }
}
