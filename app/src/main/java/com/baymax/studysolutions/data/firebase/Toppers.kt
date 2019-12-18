package com.baymax.studysolutions.data.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baymax.studysolutions.data.Topper
import com.google.firebase.firestore.FirebaseFirestore


class Toppers {
    private val firestore:FirebaseFirestore by lazy{
        FirebaseFirestore.getInstance()
    }

    private val toppersList = mutableListOf<Topper>()
    private val toppers = MutableLiveData<List<Topper>>()

    init {
        toppers.value = toppersList
    }

    fun getToppers():LiveData<List<Topper>>
    {
        firestore.collection("Toppers").get().addOnCompleteListener { task ->

            if(task.isSuccessful)
            {
                toppersList.clear()
                for(topper in task.result!!)
                {
                    Log.d("(Saquib)","data is fetched "+topper.get("name").toString())
                    toppersList.add(
                        Topper(topper.get("name").toString(),
                               topper.get("percentage").toString(),
                               topper.get("imgurl").toString(),
                               topper.get("resulturl").toString(),
                               topper.get("rollno").toString(),
                               topper.get("schoolname").toString(),
                               topper.get("session").toString(),
                               topper.get("stream").toString(),
                               topper.get("standard").toString())
                    )
                }
            }
            else
            {
                Log.d("(Saquib)","unable to fetch the data "+task.exception?.cause.toString())
            }
        }.addOnCompleteListener {
            toppers.value = toppersList
        }
        return toppers
    }
}