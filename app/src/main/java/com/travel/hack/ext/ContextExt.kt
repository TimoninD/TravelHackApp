package com.travel.hack.ext

import android.content.Context
import android.util.TypedValue

fun Context.convertDpToPx( dpValue: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
        resources.displayMetrics
    )
}