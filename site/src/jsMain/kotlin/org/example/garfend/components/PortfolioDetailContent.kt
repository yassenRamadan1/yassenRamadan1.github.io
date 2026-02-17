package org.example.garfend.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.argb
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.example.garfend.models.LinkType
import org.example.garfend.models.Portfolio
import org.example.garfend.models.Theme
import org.example.garfend.services.AppStoreMetadata
import org.example.garfend.services.AppStoreService
import org.example.garfend.styles.AppStoreButtonStyle
import org.example.garfend.styles.InfoSectionStyle
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.I
import org.jetbrains.compose.web.dom.Text

@Composable
fun PortfolioDetailContent(portfolio: Portfolio) {
    val breakpoint = rememberBreakpoint()

    // State for App Store metadata
    var appStoreData by remember { mutableStateOf<AppStoreMetadata?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var fetchError by remember { mutableStateOf(false) }

    // Determine if this app should fetch from App Store
    val shouldFetchFromAppStore = portfolio.links.appStore != null

    // Fetch App Store data if available
    LaunchedEffect(portfolio) {
        if (shouldFetchFromAppStore) {
            isLoading = true
            fetchError = false

            val metadata = AppStoreService.fetchAppMetadata(portfolio.links.appStore!!)
            if (metadata != null) {
                appStoreData = metadata
                console.log("Successfully fetched App Store data for ${portfolio.title}")
            } else {
                fetchError = true
                console.error("Failed to fetch App Store data for ${portfolio.title}")
            }
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .gap(40.px),
        horizontalAlignment = Alignment.Start
    ) {
        // Download Buttons & Rating
        DownloadSection(
            portfolio = portfolio,
            breakpoint = breakpoint,
            appStoreData = appStoreData,
            isLoading = isLoading,
            fetchError = fetchError,
            shouldFetchFromAppStore = shouldFetchFromAppStore
        )

        // About This App
        AboutSection(
            portfolio = portfolio,
            appStoreData = appStoreData,
            isLoading = isLoading,
            fetchError = fetchError,
            shouldFetchFromAppStore = shouldFetchFromAppStore
        )

        // App Information
        AppInfoSection(
            portfolio = portfolio,
            appStoreData = appStoreData,
            isLoading = isLoading,
            fetchError = fetchError,
            shouldFetchFromAppStore = shouldFetchFromAppStore
        )
    }
}

@Composable
private fun DownloadSection(
    portfolio: Portfolio,
    breakpoint: Breakpoint,
    appStoreData: AppStoreMetadata?,
    isLoading: Boolean,
    fetchError: Boolean,
    shouldFetchFromAppStore: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .gap(16.px)
    ) {
        // Rating and reviews - only show if we have App Store data OR if app doesn't need it
        if (shouldFetchFromAppStore) {
            // Show rating/version from API or error state
            if (isLoading || appStoreData != null || fetchError) {
                Row(
                    modifier = Modifier
                        .gap(20.px),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when {
                        isLoading -> {
                            // Loading state
                            org.jetbrains.compose.web.dom.Span(
                                attrs = Modifier
                                    .fontSize(16.px)
                                    .color(Theme.Secondary.rgb)
                                    .opacity(60.percent)
                                    .fontFamily("Space Grotesk", "sans-serif")
                                    .toAttrs()
                            ) {
                                Text("Loading app data from App Store...")
                            }
                        }
                        fetchError -> {
                            // Error state
                            org.jetbrains.compose.web.dom.Span(
                                attrs = Modifier
                                    .fontSize(16.px)
                                    .color(argb(a = 1f, r = 220, g = 38, b = 38))
                                    .fontFamily("Space Grotesk", "sans-serif")
                                    .toAttrs()
                            ) {
                                Text("⚠ Failed to load app data from App Store")
                            }
                        }
                        appStoreData != null -> {
                            // Success state - show fetched data
                            val rating = AppStoreService.formatRating(appStoreData.averageUserRating)
                            val version = appStoreData.version
                            val ratingCount = appStoreData.userRatingCount

                            if (rating != "N/A" && rating != "0.0") {
                                // Rating
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.gap(4.px)
                                ) {
                                    org.jetbrains.compose.web.dom.Span(
                                        attrs = Modifier
                                            .fontSize(if (breakpoint >= Breakpoint.MD) 48.px else 36.px)
                                            .fontWeight(FontWeight.Bold)
                                            .color(Theme.Primary.rgb)
                                            .fontFamily("Space Grotesk", "sans-serif")
                                            .toAttrs()
                                    ) {
                                        Text(rating)
                                    }
                                    org.jetbrains.compose.web.dom.Span(
                                        attrs = Modifier
                                            .fontSize(12.px)
                                            .color(Theme.Secondary.rgb)
                                            .opacity(60.percent)
                                            .fontFamily("Space Grotesk", "sans-serif")
                                            .toAttrs()
                                    ) {
                                        val stars = "★★★★★"
                                        val ratingCountText = if (ratingCount > 0) " (${AppStoreService.formatRatingCount(ratingCount)})" else ""
                                        Text("$stars$ratingCountText")
                                    }
                                }

                                // Divider
                                org.jetbrains.compose.web.dom.Div(
                                    attrs = Modifier
                                        .width(1.px)
                                        .height(50.px)
                                        .backgroundColor(Theme.Secondary.rgb)
                                        .opacity(20.percent)
                                        .toAttrs()
                                )
                            }

                            // Version
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.gap(4.px)
                            ) {
                                org.jetbrains.compose.web.dom.Span(
                                    attrs = Modifier
                                        .fontSize(14.px)
                                        .fontWeight(FontWeight.SemiBold)
                                        .color(Theme.Primary.rgb)
                                        .fontFamily("Space Grotesk", "sans-serif")
                                        .toAttrs()
                                ) {
                                    Text(version)
                                }
                                org.jetbrains.compose.web.dom.Span(
                                    attrs = Modifier
                                        .fontSize(12.px)
                                        .color(Theme.Secondary.rgb)
                                        .opacity(60.percent)
                                        .fontFamily("Space Grotesk", "sans-serif")
                                        .toAttrs()
                                ) {
                                    Text("Version")
                                }
                            }
                        }
                    }
                }
            }
        } else if (portfolio.rating != "N/A") {
            // Non-App Store apps: show hardcoded rating/version
            Row(
                modifier = Modifier
                    .gap(20.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.gap(4.px)
                ) {
                    org.jetbrains.compose.web.dom.Span(
                        attrs = Modifier
                            .fontSize(if (breakpoint >= Breakpoint.MD) 48.px else 36.px)
                            .fontWeight(FontWeight.Bold)
                            .color(Theme.Primary.rgb)
                            .fontFamily("Space Grotesk", "sans-serif")
                            .toAttrs()
                    ) {
                        Text(portfolio.rating)
                    }
                    org.jetbrains.compose.web.dom.Span(
                        attrs = Modifier
                            .fontSize(12.px)
                            .color(Theme.Secondary.rgb)
                            .opacity(60.percent)
                            .fontFamily("Space Grotesk", "sans-serif")
                            .toAttrs()
                    ) {
                        Text("★★★★★")
                    }
                }

                org.jetbrains.compose.web.dom.Div(
                    attrs = Modifier
                        .width(1.px)
                        .height(50.px)
                        .backgroundColor(Theme.Secondary.rgb)
                        .opacity(20.percent)
                        .toAttrs()
                )

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.gap(4.px)
                ) {
                    org.jetbrains.compose.web.dom.Span(
                        attrs = Modifier
                            .fontSize(14.px)
                            .fontWeight(FontWeight.SemiBold)
                            .color(Theme.Primary.rgb)
                            .fontFamily("Space Grotesk", "sans-serif")
                            .toAttrs()
                    ) {
                        Text(portfolio.version)
                    }
                    org.jetbrains.compose.web.dom.Span(
                        attrs = Modifier
                            .fontSize(12.px)
                            .color(Theme.Secondary.rgb)
                            .opacity(60.percent)
                            .fontFamily("Space Grotesk", "sans-serif")
                            .toAttrs()
                    ) {
                        Text("Version")
                    }
                }
            }
        }

        // Download buttons
        if (portfolio.links.hasLinks()) {
            SimpleGrid(
                numColumns = numColumns(base = 1, sm = portfolio.links.getAllLinks().size.coerceAtMost(2)),
                modifier = Modifier
                    .fillMaxWidth()
                    .gap(12.px)
            ) {
                portfolio.links.getAllLinks().forEach { (linkType, url) ->
                    DownloadButton(linkType = linkType, url = url)
                }
            }
        }
    }
}

@Composable
private fun DownloadButton(linkType: LinkType, url: String) {
    Link(
        path = url,
        openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
        modifier = AppStoreButtonStyle.toModifier()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .gap(12.px),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            I(attrs = Modifier
                .fontSize(24.px)
                .color(Theme.Primary.rgb)
                .toAttrs {
                    linkType.icon.split(" ").forEach { classes(it) }
                }
            )

            // Label
            org.jetbrains.compose.web.dom.Span(
                attrs = Modifier
                    .fontSize(16.px)
                    .fontWeight(FontWeight.Medium)
                    .color(Theme.Primary.rgb)
                    .fontFamily("Space Grotesk", "sans-serif")
                    .toAttrs()
            ) {
                Text(linkType.label)
            }
        }
    }
}

@Composable
private fun AboutSection(
    portfolio: Portfolio,
    appStoreData: AppStoreMetadata?,
    isLoading: Boolean,
    fetchError: Boolean,
    shouldFetchFromAppStore: Boolean
) {
    Column(
        modifier = InfoSectionStyle.toModifier()
            .fillMaxWidth()
            .gap(16.px)
    ) {
        H2(
            attrs = Modifier
                .margin(topBottom = 0.px)
                .fontSize(24.px)
                .fontWeight(FontWeight.Bold)
                .color(Theme.Primary.rgb)
                .fontFamily("Space Grotesk", "sans-serif")
                .toAttrs()
        ) {
            Text("About this app")
        }

        when {
            shouldFetchFromAppStore && isLoading -> {
                org.jetbrains.compose.web.dom.P(
                    attrs = Modifier
                        .margin(topBottom = 0.px)
                        .fontSize(16.px)
                        .lineHeight(1.6)
                        .color(Theme.Secondary.rgb)
                        .opacity(60.percent)
                        .fontFamily("Space Grotesk", "sans-serif")
                        .toAttrs()
                ) {
                    Text("Loading description from App Store...")
                }
            }
            shouldFetchFromAppStore && fetchError -> {
                org.jetbrains.compose.web.dom.P(
                    attrs = Modifier
                        .margin(topBottom = 0.px)
                        .fontSize(16.px)
                        .lineHeight(1.6)
                        .color(argb(a = 1f, r = 220, g = 38, b = 38))
                        .fontFamily("Space Grotesk", "sans-serif")
                        .toAttrs()
                ) {
                    Text("⚠ Failed to load description from App Store. Please try again later.")
                }
            }
            shouldFetchFromAppStore && appStoreData != null -> {
                org.jetbrains.compose.web.dom.P(
                    attrs = Modifier
                        .margin(topBottom = 0.px)
                        .fontSize(16.px)
                        .lineHeight(1.6)
                        .color(Theme.Primary.rgb)
                        .opacity(80.percent)
                        .fontFamily("Space Grotesk", "sans-serif")
                        .toAttrs()
                ) {
                    Text(appStoreData.description)
                }
            }
            !shouldFetchFromAppStore && portfolio.appDescription.isNotEmpty() -> {
                // Non-App Store apps: show hardcoded description
                org.jetbrains.compose.web.dom.P(
                    attrs = Modifier
                        .margin(topBottom = 0.px)
                        .fontSize(16.px)
                        .lineHeight(1.6)
                        .color(Theme.Primary.rgb)
                        .opacity(80.percent)
                        .fontFamily("Space Grotesk", "sans-serif")
                        .toAttrs()
                ) {
                    Text(portfolio.appDescription)
                }
            }
        }
    }
}

@Composable
private fun AppInfoSection(
    portfolio: Portfolio,
    appStoreData: AppStoreMetadata?,
    isLoading: Boolean,
    fetchError: Boolean,
    shouldFetchFromAppStore: Boolean
) {
    Column(
        modifier = InfoSectionStyle.toModifier()
            .fillMaxWidth()
            .gap(20.px)
    ) {
        H3(
            attrs = Modifier
                .margin(topBottom = 0.px)
                .fontSize(20.px)
                .fontWeight(FontWeight.Bold)
                .color(Theme.Primary.rgb)
                .fontFamily("Space Grotesk", "sans-serif")
                .toAttrs()
        ) {
            Text("App information")
        }

        // Static info rows (always shown - these are inherent to the app)
        InfoRow(label = "Developer", value = portfolio.developer)
        InfoRow(label = "Category", value = portfolio.description.title)

        // Platform (static)
        val platform = when {
            portfolio.links.isCrossPlatform() -> "iOS & Android"
            portfolio.links.playStore != null -> "Android"
            portfolio.links.appStore != null -> "iOS"
            portfolio.links.website != null -> "Web"
            portfolio.links.github != null -> "Open Source"
            portfolio.links.figma != null -> "Design"
            else -> "Other"
        }
        InfoRow(label = "Platform", value = platform)

        // Status (static)
        val statusText = when (portfolio.status) {
            org.example.garfend.models.DevelopmentStatus.PRODUCTION -> "Available"
            org.example.garfend.models.DevelopmentStatus.IN_DEVELOPMENT -> "In Development"
            org.example.garfend.models.DevelopmentStatus.IN_TESTING -> "In Testing"
        }
        InfoRow(label = "Status", value = statusText)

        // Dynamic data from App Store (or show error/loading)
        if (shouldFetchFromAppStore) {
            when {
                isLoading -> {
                    InfoRow(label = "Version", value = "Loading...")
                    InfoRow(label = "Rating", value = "Loading...")
                    InfoRow(label = "Details", value = "Fetching from App Store...")
                }
                fetchError -> {
                    InfoRow(
                        label = "App Store Data",
                        value = "⚠ Failed to load",
                        isError = true
                    )
                }
                appStoreData != null -> {
                    // Version
                    InfoRow(label = "Version", value = appStoreData.version)

                    // Rating
                    val rating = AppStoreService.formatRating(appStoreData.averageUserRating)
                    if (rating != "N/A" && rating != "0.0") {
                        val ratingText = if (appStoreData.userRatingCount > 0) {
                            "$rating/5.0 (${AppStoreService.formatRatingCount(appStoreData.userRatingCount)} ratings)"
                        } else {
                            "$rating/5.0"
                        }
                        InfoRow(label = "Rating", value = ratingText)
                    }

                    // Price
                    val priceText = AppStoreService.formatPrice(appStoreData.price, appStoreData.formattedPrice)
                    InfoRow(label = "Price", value = priceText)

                    // File size
                    if (appStoreData.fileSizeBytes.isNotEmpty()) {
                        InfoRow(label = "Size", value = AppStoreService.formatFileSize(appStoreData.fileSizeBytes))
                    }

                    // Minimum OS version
                    if (appStoreData.minimumOsVersion.isNotEmpty()) {
                        InfoRow(label = "Requires iOS", value = "${appStoreData.minimumOsVersion} or later")
                    }

                    // Age rating
                    if (appStoreData.contentAdvisoryRating.isNotEmpty()) {
                        InfoRow(label = "Age Rating", value = appStoreData.contentAdvisoryRating)
                    }

                    // Genres
                    if (appStoreData.genres.isNotEmpty()) {
                        InfoRow(label = "Genres", value = appStoreData.genres.joinToString(", "))
                    }
                }
            }
        } else {
            // Non-App Store apps: show hardcoded data if available
            if (portfolio.version.isNotEmpty()) {
                InfoRow(label = "Version", value = portfolio.version)
            }
            if (portfolio.rating != "N/A") {
                InfoRow(label = "Rating", value = "${portfolio.rating}/5.0")
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String, isError: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(topBottom = 8.px),
        verticalAlignment = Alignment.Top
    ) {
        org.jetbrains.compose.web.dom.Span(
            attrs = Modifier
                .fontSize(15.px)
                .color(Theme.Primary.rgb)
                .opacity(60.percent)
                .fontFamily("Space Grotesk", "sans-serif")
                .minWidth(120.px)
                .toAttrs()
        ) {
            Text(label)
        }

        org.jetbrains.compose.web.dom.Span(
            attrs = Modifier
                .fontSize(15.px)
                .color(if (isError) argb(a = 1f, r = 220, g = 38, b = 38) else Theme.Primary.rgb)
                .fontFamily("Space Grotesk", "sans-serif")
                .toAttrs()
        ) {
            Text(value)
        }
    }
}
