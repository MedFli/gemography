package github.challenge.gemography.config

import github.challenge.gemography.model.ListRepos
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RepoConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}