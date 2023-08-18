package com.example.rxmvvm3app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.rxmvvm3app.R
import com.example.rxmvvm3app.data.network.RetrofitService
import com.example.rxmvvm3app.data.repository.MainRepository
import com.example.rxmvvm3app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(MainRepository(retrofitService))
        ).get(MainViewModel::class.java)

        binding.recyclerView.adapter = adapter

        binding.buttonShowUsers.setOnClickListener {
            viewModel.getAllUsersList()
            binding.buttonShowUsers.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE

        }

        viewModel.usersList.observe(this, Observer {
            if (it != null) {
                Log.d(TAG, "userList: $it")
                adapter.setData(it.usersList)
            } else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })

        //invoked when a network exception occurred
        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })
    }
    override fun onDestroy() {
        //don't send events  once the activity is destroyed
        viewModel.disposable.dispose()
        super.onDestroy()
    }
}