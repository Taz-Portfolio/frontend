package api

import androidx.compose.runtime.MutableState
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object API : KMMViewModel() {
    const val DOMAIN = "http://localhost:8080"

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    }

    inline fun <reified T> fetch(endpoint: Endpoint, mutableState: MutableState<T>) {
        viewModelScope.coroutineScope.launch {
            mutableState.value = client.get(DOMAIN + endpoint.path).body<T>()
        }
    }
}