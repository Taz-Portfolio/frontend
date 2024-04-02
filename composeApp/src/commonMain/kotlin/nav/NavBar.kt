package nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
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
    var isSelected: MutableState<Boolean>,
) {
    fun select() {
        isSelected.value = true
    }

    fun deselect() {
        isSelected.value = false
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FloatingNav(navigator: Navigator, navItems: MutableMap<NavItem, Screen>) {
    val windowSizeClass = calculateWindowSizeClass()

    val width = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> 0.5f
        WindowWidthSizeClass.Medium -> 0.7f
        WindowWidthSizeClass.Compact -> 0.9f
        else -> 1f
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        NavigationBar(
            modifier = Modifier.clip(CircleShape).fillMaxWidth(width).height(60.dp),
            containerColor = Color.Black.copy(alpha = 0.5f),
            tonalElevation = 0.dp
        ) {
            navItems.forEach { (navItem, _) ->
                NavigationBarItem(
                    selected = navItem.isSelected.value,
                    onClick = { changeSelection(navItems, navItem, navigator) },
                    icon = {
                        if (navItem.isSelected.value) Icon(
                            navItem.selectedIcon,
                            contentDescription = null,
                            tint = Color.DarkGray
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

fun changeSelection(navItems: Map<NavItem, Screen>, newSelection: NavItem, navigator: Navigator) {
    val currentSelection = navItems.keys.first { it.isSelected.value }

    if (currentSelection != newSelection) {
        currentSelection.deselect()
        newSelection.select()
        navigator.navigate(navItems[newSelection]!!.route)
    }
}
