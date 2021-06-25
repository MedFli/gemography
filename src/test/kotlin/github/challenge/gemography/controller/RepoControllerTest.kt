package github.challenge.gemography.controller

import github.challenge.gemography.model.ListRepos
import github.challenge.gemography.model.Owner
import github.challenge.gemography.model.Repos
import github.challenge.gemography.service.ReposService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.format.DateTimeFormatter

import java.time.LocalDateTime

@WebMvcTest(RepoController::class)
class RepoControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var reposService: ReposService

    val now = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formattedDate = now.format(formatter)
    val REPO_ENDPOINT = "/repositories/{language}"

    @Test
    fun `should return return list repositories given language`() {
        val LANGUAGE = "java"
        val listRepo = ListRepos(
            items = listOf(
                Repos(
                    id = 12345,
                    name = "junior",
                    languageUsed = "kotlin",
                    createAt = formattedDate,
                    owner = Owner(
                        id = 99999,
                        login = "junior"
                    ), description = "github coding challenge for gemography"
                ),
                Repos(
                    id = 12346,
                    name = "Jadamine",
                    languageUsed = "Java",
                    createAt = formattedDate,
                    owner = Owner(
                        id = 88888,
                        login = "jadamine"
                    ), description = "java repository"
                ),
            )
        )
        every { reposService.importRepositoriesJson(LANGUAGE) } returns listRepo.items
        mockMvc.perform(
            MockMvcRequestBuilders.get(REPO_ENDPOINT, LANGUAGE)
                .param("language", LANGUAGE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
    }

    @TestConfiguration
    internal class ControllerTestConfig {
        @Bean
        fun mockChannelService() = mockk<ReposService>()
    }
}