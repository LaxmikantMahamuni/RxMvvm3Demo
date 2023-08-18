package com.example.rxmvvm3app.data.network

import com.example.rxmvvm3app.data.network.model.ResponseallUsers
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
 @GET("api/users?page=2")
 fun getAllUsers(): Observable<ResponseallUsers>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit= Retrofit.Builder()
                    .baseUrl("https://reqres.in/") //https://www.reqres.in/ - does not work
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}