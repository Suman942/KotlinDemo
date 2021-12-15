package com.example.netclankotlin.explore.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.netclankotlin.R
import com.example.netclankotlin.databinding.ActivitySecondBinding
import com.example.netclankotlin.explore.Data
import com.example.netclankotlin.explore.adapters.ExploreAdapter
import com.example.netclankotlin.otp.VerifyOtpViewModel
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var exploreAdapter: ExploreAdapter
    val exploreList = ArrayList<Data>()
    lateinit var viewModel: VerifyOtpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        viewModel = ViewModelProvider(this).get(VerifyOtpViewModel::class.java)

        GlobalScope.launch {
            viewModel.getExploreData(1)
        }

        getData()
        initialise()
    }

    private fun getData() {
        viewModel.exploreLiveData.observe(this@SecondActivity, Observer {
            Log.d("exception","observer: "+Thread.currentThread())

            exploreList.addAll(it.data)
            exploreAdapter.notifyDataSetChanged()
        })
    }

    private fun initialise() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        exploreAdapter = ExploreAdapter(this, exploreList)
        binding.recyclerView.adapter = exploreAdapter
    }


}