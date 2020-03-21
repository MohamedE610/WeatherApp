package com.example.weatherapp.core.presentation.extentions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapp.core.presentation.di.module.GlideApp


fun ImageView.loadFromUrl(
    url: String?,
    placeholder: Int? = null,
    error: Int? = null,
    listener: RequestListener<Drawable>? = null
) {
    val glideApp = GlideApp.with(context)
        .load(url)

    placeholder?.let {
        glideApp.apply(RequestOptions().placeholder(it))
    }
    error?.let {
        glideApp.apply(RequestOptions().error(it))
    }

    listener?.let {
        glideApp.listener(it)
    }

    glideApp.into(this)
}
