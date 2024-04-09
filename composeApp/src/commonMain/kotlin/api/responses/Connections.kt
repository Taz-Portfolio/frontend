package api.responses

import api.responses.model.Connection
import kotlinx.serialization.Serializable

@Serializable
data class Connections(val connections: List<Connection> = listOf())
