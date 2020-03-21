package com.example.weatherapp.core.presentation.extentions

import android.animation.ObjectAnimator
import android.view.View


fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.animateFadeOut(
    delay: Long,
    startDelay: Long = 0,
    from: Float = 1f,
    to: Float = 0f
): ObjectAnimator? {
    val fadeOutAnimation = ObjectAnimator.ofFloat(this, View.ALPHA, from, to)
    fadeOutAnimation.duration = delay
    fadeOutAnimation.startDelay = startDelay
    fadeOutAnimation.start()
    return fadeOutAnimation
}

fun View.animateFadeIn(
    delay: Long,
    startDelay: Long = 0,
    from: Float = 0f,
    to: Float = 1f
): ObjectAnimator? {
    val fadeInAnimation = ObjectAnimator.ofFloat(this, View.ALPHA, from, to)
    fadeInAnimation.duration = delay
    fadeInAnimation.startDelay = startDelay
    fadeInAnimation.start()
    return fadeInAnimation
}

