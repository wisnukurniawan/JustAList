package com.wisnu.justalist.data.remote.dribble.model

import java.util.*

/**
 * Created by wisnu on 11/28/18
 */
data class Shot(
    val id: Long?,
    val title: String?,
    val description: String?,
    val images: Images?,
    val viewsCount: Int? = 0,
    val likesCount: Int? = 0,
    val createdAt: Date? = null,
    val htmlUrl: String? = "https://dribbble.com/shots/$id",
    val animated: Boolean = false,
    val user: User?
) {
    var hasFadedIn = false
}