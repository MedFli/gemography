package github.challenge.gemography.controller

import github.challenge.gemography.model.Repos
import github.challenge.gemography.service.ReposService
import github.challenge.gemography.util.loggerFor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException


@RestController
@RequestMapping("/repositories")
class RepoController(
    private val reposService: ReposService
) {
    private val logger = loggerFor(this::class.java)

    @GetMapping(value = ["/{language}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @Throws(IOException::class)
    fun getAllRepositories(
        @PathVariable("language", required = false) language: String
    ): ResponseEntity<List<Repos>> {
        logger.info("Incoming request to get all repositories in github open Api")
        val repositories = reposService.importRepositoriesJson(language)
        return ResponseEntity(repositories, HttpStatus.OK)
    }
}