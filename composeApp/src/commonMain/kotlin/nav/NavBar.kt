package nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import screen.Screen

data class NavItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
    var selected: MutableState<Boolean>,
)

@Composable
fun FloatingNav(navigator: Navigator, navItems: MutableMap<NavItem, Screen>) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        BottomNavigation(
            modifier = Modifier.clip(CircleShape).fillMaxWidth(0.5f),
            backgroundColor = Color.Black.copy(alpha = 0.5f),
            elevation = 0.dp
        ) {
            navItems.forEach { (navItem, screen) ->
                BottomNavigationItem(
                    selected = navItem.selected.value,
                    onClick = {
                        navItems.keys.forEach { it.selected.value = it == navItem }
                        navigator.navigate(screen.route)
                    },
                    icon = {
                        if (navItem.selected.value) Icon(
                            navItem.selectedIcon,
                            contentDescription = null,
                            modifier = Modifier.background(Color.DarkGray, CircleShape).width(50.dp),
                            tint = Color.White
                        )
                        else Icon(
                            navItem.unselectedIcon,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(text = navItem.label, color = Color.White)
                    },
                )
            }
        }
    }
}