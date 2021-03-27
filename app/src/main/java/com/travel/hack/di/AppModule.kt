package com.travel.hack.di

import com.travel.hack.model.Prefs
import com.travel.hack.navigation.NavControllerNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModule {
    val module = module {
        //Navigation
        scope(named(navScopeScopeName)) {
            scoped { NavControllerNavigator() }
        }

        single { Prefs(androidContext()) }
    }
}

const val globalNavScopeId = "NAVIGATION_GLOBAL"
const val navScopeScopeName = "NAV_SCOPE"

/**
 * Формирует имя скоупа на labl'у графа
 *
 * @param graphLabel имя скоупа с приставкой NAVIGATION_
 */

fun getNavScopeName(graphLabel: CharSequence) = "NAVIGATION_$graphLabel"