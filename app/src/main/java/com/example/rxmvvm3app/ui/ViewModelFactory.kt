package com.example.rxmvvm3app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rxmvvm3app.data.repository.MainRepository
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val mainRepository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.mainRepository) as T
        }else {
            throw IllegalArgumentException("View model not found.")
        }
    }
}