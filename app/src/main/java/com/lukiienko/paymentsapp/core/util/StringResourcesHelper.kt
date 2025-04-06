package com.lukiienko.paymentsapp.core.util

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringResourcesHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun get(@StringRes resId: Int): String = context.getString(resId)
}
