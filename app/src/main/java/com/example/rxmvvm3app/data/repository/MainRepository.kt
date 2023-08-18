package com.example.rxmvvm3app.data.repository

import com.example.rxmvvm3app.data.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllUsers() = retrofitService.getAllUsers()
}