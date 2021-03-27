package com.travel.hack.navigation

import androidx.navigation.NavDirections

interface Navigator {
    fun to(directions: NavDirections)
    fun back(): Boolean
    fun backTo(destinationId: Int, inclusive: Boolean): Boolean
}