package api

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import api.model.Info
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object DataAPI : KMMViewModel() {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    }

    fun fetchInfo(): MutableState<Info> {
        val mutableState: MutableState<Info> = mutableStateOf(Info())

        viewModelScope.coroutineScope.launch {
            mutableState.value = client.get("http://localhost:8080/info").body<Info>()
        }
        return mutableState
    }
}