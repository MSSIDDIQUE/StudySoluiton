package com.baymax.studysolutions.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.baymax.studysolutions.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
class AuthViewModel(private val repository: Repository):ViewModel(){
    var email:String? = null
    var password: String? = null

    var authListener:AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    fun login()
    {
        if(email.isNullOrBlank()||password.isNullOrBlank())
        {
            authListener?.onFailure("invalid user name and password")
            return
        }
        authListener?.onStarted()

        val disposable = repository.login(email!!,password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {authListener?.onSuccess()},{authListener?.onFailure(it.message!!)}
            )
        disposables.add(disposable)
    }

    fun logout()
    {
        repository.logout()
        authListener?.onSuccess()
    }

    fun signup(){
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun goToSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    //disposing the disposables
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}