import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import nav.FloatingNav
import nav.NavItem
import screen.ContactScreen
import screen.HomeScreen
import screen.MoreScreen
import screen.ProjectsScreen

val navItems = mutableMapOf(
    NavItem(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        label = "Home",
        isSelected = mutableStateOf(true)
    ) to HomeScreen,
    NavItem(
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
        label = "Projects",
        isSelected = mutableStateOf(false)
    ) to ProjectsScreen,
    NavItem(
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
        label = "Contact",
        isSelected = mutableStateOf(false)
    ) to ContactScreen,
    NavItem(
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        label = "More",
        isSelected = mutableStateOf(false)
    ) to MoreScreen
)

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        MaterialTheme {
            Scaffold(
                bottomBar = { FloatingNav(navigator, navItems) }
            ) {
                NavHost(
                    navigator = navigator,
                    initialRoute = HomeScreen.route
                ) {
                    navItems.forEach { (_, screen) -> scene(screen.route) { screen.content() } }
                }
            }
        }
    }
}