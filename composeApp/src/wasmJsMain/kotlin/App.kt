import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import nav.FloatingNav
import nav.NavItem
import org.koin.compose.KoinContext
import screen.ContactScreen
import screen.HomeScreen
import screen.MoreScreen
import screen.ProjectsScreen

val navItems = mapOf(
    NavItem(Icons.Filled.Home, "Home") to HomeScreen,
    NavItem(Icons.Filled.Star, "Projects") to ProjectsScreen,
    NavItem(Icons.Filled.Email, "Contact Me") to ContactScreen,
    NavItem(Icons.Filled.MoreVert, "More") to MoreScreen
)

@Composable
fun App() {
    PreComposeApp {
        KoinContext {
            val navigator = rememberNavigator()
            MaterialTheme {
                Scaffold(
                    bottomBar = { FloatingNav(navigator, navItems) }
                ) {
                    NavHost(
                        navigator = navigator,
                        initialRoute = HomeScreen.route
                    ) {
                        navItems.forEach { (_, screen) ->
                            scene(screen.route) { screen.content() }
                        }
                    }
                }
            }
        }
    }
}