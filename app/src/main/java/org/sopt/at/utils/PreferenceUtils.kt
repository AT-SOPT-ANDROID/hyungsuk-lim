package org.sopt.at.utils

import android.content.Context
import org.sopt.at.R

fun getUserIdFromPrefs(context: Context): Int {
    val prefs = context.getSharedPreferences(
        context.getString(R.string.tving_preference_key),
        Context.MODE_PRIVATE
    )
    return prefs.getInt(context.getString(R.string.access_token_key), -1)
}