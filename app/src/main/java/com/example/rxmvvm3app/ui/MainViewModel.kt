package com.example.rxmvvm3app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxmvvm3app.data.network.model.ResponseallUsers
import com.example.rxmvvm3app.data.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel(){
    val usersList = MutableLiveData<ResponseallUsers>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable

    fun getAllUsersList(){
        /*Both line(val response & response) must be start on same column*/
        val response = mainRepository.getAllUsers()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getAllUserListObserver())
    }
    fun getAllUserListObserver(): Observer<ResponseallUsers> {
        return object : Observer<ResponseallUsers> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
                //start showing progress loader
            }

            override fun onError(e: Throwable) {
                errorMessage.postValue(e.message)
            }

            override fun onComplete() {
                //stop showing progress loader
            }

            override fun onNext(t: ResponseallUsers) {
                usersList.postValue(t)
            }
        }
    }
}