package com.wisnu.justalist.data.remote.dribble.model

/**
 * Created by wisnu on 11/28/18
 */
data class User(
    val id: Long?,
    val name: String?,
    val username: String?,
    val avatarUrl: String?
) {

    val highQualityAvatarUrl: String by lazy {
        avatarUrl?.replace("/normal/", "/original/") ?: ""
    }
}