package com.baymax.studysolutions.ui

import android.app.Application
import com.baymax.studysolutions.data.Repository
import com.baymax.studysolutions.data.firebase.Login
import com.baymax.studysolutions.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class StudySolutionApplication:Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@StudySolutionApplication))
        bind() from singleton { Login() }
        bind() from singleton { Repository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}