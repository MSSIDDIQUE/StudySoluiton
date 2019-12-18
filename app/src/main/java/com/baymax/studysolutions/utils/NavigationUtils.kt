package com.baymax.studysolutions.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.baymax.studysolutions.MainActivity
import com.baymax.studysolutions.ui.auth.LoginActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun Context.startMainActivity() =
    Intent(this, MainActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Log.d("Picasso","image is loading from url "+url.toString())
    Picasso.get().load(url.toString()).fit().centerCrop().into(this, object : Callback {
        override fun onSuccess() {
            Log.d("Picasso", "success")
        }

        override fun onError(e: Exception?) {
            Log.d("Picasso", "error"+e?.printStackTrace())
        }
    })
    //Glide.with(this).load(url).centerCrop().into(this)
}