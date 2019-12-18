package com.baymax.studysolutions.data

import com.baymax.studysolutions.data.firebase.Login
import com.baymax.studysolutions.data.firebase.Toppers

class Repository (private val firebaseLogin : Login,
                  private val firebaseToppers: Toppers
){
    fun login(email: String, password: String) = firebaseLogin.login(email, password)

    fun register(email: String, password: String) = firebaseLogin.register(email, password)

    fun currentUser() = firebaseLogin.currentUser()

    fun logout() = firebaseLogin.logout()

    fun getToppers() = firebaseToppers.getToppers()
}