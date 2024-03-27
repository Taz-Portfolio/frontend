package nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import moe.tlaster.precompose.navigation.Navigator
import screen.HomeScreen
import screen.Screen

data class NavItem(
    val icon: ImageVector,
    val label: String,
)

@Composable
fun FloatingNav(navigator: Navigator, navItems: Map<NavItem, Screen>) {
    BottomNavigation {
        navItems.forEach { (navItem, screen) ->
            BottomNavigationItem(
                selected = screen == HomeScreen,
                onClick = { navigator.navigate(screen.route) },
                icon = { Icon(navItem.icon, contentDescription = null) },
                label = { Text(navItem.label) },
            )
        }
    }
}