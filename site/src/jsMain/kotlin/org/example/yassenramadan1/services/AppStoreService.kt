package org.example.yassenramadan1.services

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.delay

/**
 * Data class representing app metadata from iTunes API
 */
data class AppStoreMetadata(
    val description: String = "",
    val averageUserRating: Double = 0.0,
    val userRatingCount: Int = 0,
    val version: String = "",
    val currentVersionReleaseDate: String = "",
    val fileSizeBytes: String = "",
    val minimumOsVersion: String = "",
    val contentAdvisoryRating: String = "",
    val trackName: String = "",
    val sellerName: String = "",
    val price: Double = 0.0,
    val formattedPrice: String = "",
    val releaseNotes: String = "",
    val genres: List<String> = emptyList()
)

/**
 * Service for fetching app metadata from iTunes Search API
 */
object AppStoreService {

    /**
     * Extract App ID from App Store URL
     * Example: https://apps.apple.com/sa/app/fromscratch/id6754660941?l=ar -> 6754660941
     */
    fun extractAppId(appStoreUrl: String): String? {
        val regex = Regex("""id(\d+)""")
        return regex.find(appStoreUrl)?.groupValues?.get(1)
    }

    /**
     * Fetch app metadata from iTunes API with retry logic
     * Returns null if fetch fails or app not found after all retries
     */
    suspend fun fetchAppMetadata(appStoreUrl: String): AppStoreMetadata? {
        val maxRetries = 3
        val retryDelayMs = 1000L // 1 second between retries

        repeat(maxRetries) { attempt ->
            val attemptNumber = attempt + 1
            console.log("=== APP STORE FETCH ATTEMPT $attemptNumber/$maxRetries ===")

            val result = try {
                console.log("URL: $appStoreUrl")

                val appId = extractAppId(appStoreUrl)
                console.log("Extracted App ID: $appId")

                if (appId == null) {
                    console.error("Failed to extract App ID")
                    null
                } else {
                    val apiUrl = "https://itunes.apple.com/lookup?id=$appId"
                    console.log("Fetching from: $apiUrl")

                    try {
                        val response = window.fetch(apiUrl).await()
                        console.log("Response received - Status: ${response.status}")

                        if (!response.ok) {
                            console.error("Bad response: ${response.status}")
                            null
                        } else {
                            val text = response.text().await()
                            console.log("Response text length: ${text.length}")

                            try {
                                // Use JavaScript's JSON.parse instead of kotlinx.serialization
                                val parsed = JSON.parse<dynamic>(text)
                                val resultCount = parsed.resultCount as Int
                                console.log("JSON parsed - Results: $resultCount")

                                if (resultCount == 0) {
                                    console.warn("No results found")
                                    null
                                } else {
                                    val app = parsed.results[0]
                                    val trackName = app.trackName as? String ?: ""
                                    console.log("Success! App: $trackName")

                                    // Extract genres array
                                    val genresList = mutableListOf<String>()
                                    val genresArray = app.genres
                                    if (genresArray != null && genresArray != undefined) {
                                        val length = genresArray.length as Int
                                        for (i in 0 until length) {
                                            genresList.add(genresArray[i] as String)
                                        }
                                    }

                                    AppStoreMetadata(
                                        description = app.description as? String ?: "",
                                        averageUserRating = (app.averageUserRating as? Number)?.toDouble() ?: 0.0,
                                        userRatingCount = (app.userRatingCount as? Number)?.toInt() ?: 0,
                                        version = app.version as? String ?: "",
                                        currentVersionReleaseDate = app.currentVersionReleaseDate as? String ?: "",
                                        fileSizeBytes = app.fileSizeBytes as? String ?: "",
                                        minimumOsVersion = app.minimumOsVersion as? String ?: "",
                                        contentAdvisoryRating = app.contentAdvisoryRating as? String ?: "",
                                        trackName = trackName,
                                        sellerName = app.sellerName as? String ?: "",
                                        price = (app.price as? Number)?.toDouble() ?: 0.0,
                                        formattedPrice = app.formattedPrice as? String ?: "",
                                        releaseNotes = app.releaseNotes as? String ?: "",
                                        genres = genresList
                                    )
                                }
                            } catch (e: dynamic) {
                                console.error("JSON parse error:")
                                console.error(e)
                                null
                            }
                        }
                    } catch (e: dynamic) {
                        console.error("Fetch error:")
                        console.error(e)
                        null
                    }
                }
            } catch (e: dynamic) {
                console.error("Outer error:")
                console.error(e)
                null
            }

            // If successful, return immediately
            if (result != null) {
                console.log("✓ Successfully fetched app data on attempt $attemptNumber")
                return result
            }

            // If this wasn't the last attempt, wait before retrying
            if (attemptNumber < maxRetries) {
                console.warn("⚠ Attempt $attemptNumber failed. Retrying in ${retryDelayMs}ms...")
                delay(retryDelayMs)
            } else {
                console.error("✗ All $maxRetries attempts failed")
            }
        }

        return null
    }

    /**
     * Format rating for display (e.g., "4.6")
     */
    fun formatRating(rating: Double): String {
        return if (rating > 0) rating.asDynamic().toFixed(1) as String else "N/A"
    }

    /**
     * Format user rating count for display (e.g., "1.2K", "45.6K", "1.2M")
     */
    fun formatRatingCount(count: Int): String {
        return when {
            count >= 1_000_000 -> "${(count / 1_000_000.0).asDynamic().toFixed(1)}M"
            count >= 1_000 -> "${(count / 1_000.0).asDynamic().toFixed(1)}K"
            else -> count.toString()
        }
    }

    /**
     * Format file size for display (e.g., "45.2 MB", "1.2 GB")
     */
    fun formatFileSize(bytes: String): String {
        val size = bytes.toLongOrNull() ?: return "N/A"
        return when {
            size >= 1_073_741_824 -> "${(size / 1_073_741_824.0).asDynamic().toFixed(1)} GB"
            size >= 1_048_576 -> "${(size / 1_048_576.0).asDynamic().toFixed(1)} MB"
            size >= 1_024 -> "${(size / 1_024.0).asDynamic().toFixed(1)} KB"
            else -> "$size B"
        }
    }

    /**
     * Format price for display
     */
    fun formatPrice(price: Double, formattedPrice: String): String {
        return if (price == 0.0) "Free" else formattedPrice.ifEmpty { "$$price" }
    }
}
