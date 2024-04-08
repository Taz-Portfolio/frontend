package api.model

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val name: String = "",
    val title: String = "",
    val resume: String = "",
    val connections: List<Connection> = listOf()
)

@Serializable
data class Connection(val icon: String, val name: String, val url: String)
