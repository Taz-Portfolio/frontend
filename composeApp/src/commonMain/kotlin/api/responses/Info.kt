package api.responses

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val name: String = "",
    val title: String = "",
    val resume: String = "",
)

