package screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import api.API
import api.Endpoint
import api.responses.Info

val info = mutableStateOf(Info())

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
data object HomeScreen: Screen("/home", {
    API.fetch(Endpoint.INFO, info)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val windowSizeClass = calculateWindowSizeClass()

        Text("Home sweet home")
        Text(text = windowSizeClass.widthSizeClass.toString())
        Text(text = windowSizeClass.heightSizeClass.toString())
        Text(text = info.value.name)
        Text(text = info.value.title)
    }
})