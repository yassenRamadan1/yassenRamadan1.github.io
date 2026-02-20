package org.example.yassenramadan1.models

import org.example.yassenramadan1.util.Res

enum class Achievement(
    val icon: String,
    val number: Int,
    val description: String,
    val descriptionKey: String
) {
    Completed(
        icon = Res.Icon.checkmark,
        number = 120,
        description = "Completed Projects",
        descriptionKey = "achievement_completed"
    ),
    Active(
        icon = Res.Icon.shield,
        number = 12,
        description = "Active Projects",
        descriptionKey = "achievement_active"
    ),
    Satisfied(
        icon = Res.Icon.happy,
        number = 42,
        description = "Satisfied Clients",
        descriptionKey = "achievement_satisfied"
    ),
    Team(
        icon = Res.Icon.user,
        number = 10,
        description = "Team Members",
        descriptionKey = "achievement_team"
    )
}
