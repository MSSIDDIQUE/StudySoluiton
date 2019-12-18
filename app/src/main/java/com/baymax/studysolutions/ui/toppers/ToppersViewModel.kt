package com.baymax.studysolutions.ui.toppers

import androidx.lifecycle.ViewModel
import com.baymax.studysolutions.data.Repository

class ToppersViewModel(private val repository: Repository):ViewModel() {

    fun getToppers() = repository.getToppers()

    }