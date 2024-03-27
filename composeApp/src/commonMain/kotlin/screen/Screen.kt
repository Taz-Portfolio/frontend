package screen

import androidx.compose.runtime.Composable

open class Screen(val route: String, val content: @Composable () -> Unit)
