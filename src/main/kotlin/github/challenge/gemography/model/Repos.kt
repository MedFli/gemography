package github.challenge.gemography.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListRepos(
    @JsonProperty("items")
    val items: List<Repos>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repos(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("createAt")
    val createAt: String,
    @JsonProperty("language")
    var languageUsed: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("owner")
    val owner: Owner
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Owner(
    @JsonProperty("login")
    val login: String?,
    val id: Long?
)