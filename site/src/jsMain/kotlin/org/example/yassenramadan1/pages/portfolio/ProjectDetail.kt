package org.example.yassenramadan1.pages.portfolio

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.example.yassenramadan1.components.BackButton
import org.example.yassenramadan1.components.PortfolioDetailContent
import org.example.yassenramadan1.components.PortfolioDetailHeader
import org.example.yassenramadan1.models.Portfolio
import org.example.yassenramadan1.models.Theme
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Page("/portfolio/{project}")
@Composable
fun ProjectDetailPage() {
    val ctx = rememberPageContext()
    val projectId = ctx.route.params["project"] ?: ""

    // Find the portfolio item by matching the urlId
    val portfolioItem = remember(projectId) {
        Portfolio.entries.firstOrNull {
            it.urlId.equals(projectId, ignoreCase = true)
        }
    }

    if (portfolioItem != null) {
        PortfolioDetailPageContent(portfolio = portfolioItem)
    } else {
        // Show 404 or redirect
        ProjectNotFound()
    }
}

@Composable
private fun PortfolioDetailPageContent(portfolio: Portfolio) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .minHeight(100.percent)
            .backgroundColor(Theme.LightGrayBg.rgb),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .maxWidth(800.px)
                .padding(topBottom = 100.px, leftRight = 25.px),
            horizontalAlignment = Alignment.Start
        ) {
            // Back button
            BackButton()

            // Portfolio header (app icon + name + developer)
            PortfolioDetailHeader(portfolio = portfolio)

            // Portfolio content (rating, download, description, info)
            PortfolioDetailContent(portfolio = portfolio)
        }
    }
}

@Composable
private fun ProjectNotFound() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .minHeight(100.percent)
            .backgroundColor(Theme.LightGrayBg.rgb),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            org.jetbrains.compose.web.dom.Text("Project not found")
            BackButton()
        }
    }
}
