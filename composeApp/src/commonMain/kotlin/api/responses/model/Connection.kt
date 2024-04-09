package api.responses.model

import kotlinx.serialization.Serializable

@Serializable
data class Connection(val icon: String, val name: String, val url: String)
