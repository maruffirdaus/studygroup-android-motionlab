package app.motion.android.ui.main

import androidx.annotation.DrawableRes
import app.motion.android.R

enum class NavItem(
    val label: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    SCHEDULE(
        "Schedule",
        R.drawable.ic_calendar_month_filled,
        R.drawable.ic_calendar_month
    ),
    ABOUT(
        "About",
        R.drawable.ic_info_filled,
        R.drawable.ic_info
    )
}