package com.wisnu.justalist.data.remote.dribble.model

/**
 * Created by wisnu on 11/28/18
 */
data class Images(
    val hidpi: String? = null,
    val normal: String? = null,
    val teaser: String? = null
) {

    fun best(): String {
        return hidpi ?: normal!!
    }

    fun bestSize(): ImageSize {
        return if (hidpi != null) ImageSize.TWO_X_IMAGE_SIZE else ImageSize.NORMAL_IMAGE_SIZE
    }

    enum class ImageSize(val width: Int, val height: Int) {
        NORMAL_IMAGE_SIZE(400, 300),
        TWO_X_IMAGE_SIZE(800, 600);

        operator fun component1() = width
        operator fun component2() = height
    }
}