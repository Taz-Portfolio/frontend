package screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import api.API
import api.Endpoint
import api.responses.Connections
import api.responses.Info
import api.responses.model.Connection
import com.seiko.imageloader.Image
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox
import com.seiko.imageloader.ui.AutoSizeImage

val info = mutableStateOf(Info())
val connections = mutableStateOf(Connections())

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
data object HomeScreen: Screen("/home", {
    val uriHandler = LocalUriHandler.current

    API.fetch(Endpoint.INFO, info)
    API.fetch(Endpoint.CONNECTIONS, connections)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val windowSizeClass = calculateWindowSizeClass()

        Text("Home sweet home")
        Text(text = windowSizeClass.widthSizeClass.toString())
        Text(text = windowSizeClass.heightSizeClass.toString())
        Name(info.value.name)
        Title(info.value.title)
        DownloadResumeButton(uriHandler, info.value.resume)
        connections.value.connections.forEach { ConnectionIcon(uriHandler, it) }
    }
})

@Composable
fun Name(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.displayLarge
    )
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun DownloadResumeButton(uriHandler: UriHandler, uri: String) {
    Button(
        onClick = { uriHandler.openUri(uri) }
    ) {
        Text(text = "Resume")
    }
}

@Composable
fun ConnectionIcon(uriHandler: UriHandler, connection: Connection) {
    AutoSizeImage(
        url = connection.icon,
        contentDescription = null,
        modifier = Modifier.height(40.dp).width(40.dp).clickable { uriHandler.openUri(connection.url) }
    )
}