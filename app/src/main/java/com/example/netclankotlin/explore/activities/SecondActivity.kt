package com.example.netclankotlin.explore.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.netclankotlin.R
import com.example.netclankotlin.databinding.ActivitySecondBinding
import com.example.netclankotlin.explore.adapters.ExploreAdapter
import com.example.netclankotlin.otp.VerifyOtpViewModel
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.netclankotlin.exploreResponse.Data
import com.example.netclankotlin.exploreResponse.ExploreResponse
import com.example.netclankotlin.room.ExploreDao
import com.example.netclankotlin.room.Explore
import com.example.netclankotlin.room.ExploreDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SecondActivity : AppCompatActivity(), ExploreAdapter.Callback {
    lateinit var binding: ActivitySecondBinding
    lateinit var exploreAdapter: ExploreAdapter
    val exploreList = ArrayList<Data>()
    lateinit var viewModel: VerifyOtpViewModel
    lateinit var dao: ExploreDao
    lateinit var db: ExploreDatabase
    var page: Int = 1
    var isLoading: Boolean = false
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        initialise()
        setData()
//        getData()
        getDataUsingRx()
        pagination()
    }

    private fun getDataUsingRx() {
        viewModel.exploreLiveData.observe(this,Observer<ExploreResponse>{
            if (it != null){
                exploreList.addAll(it.data)
                exploreAdapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this,"Error",Toast.LENGTH_LONG)
            }
        })
    }

    private fun pagination() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && !isLoading) {
                    val currentVisibleItem = linearLayoutManager.childCount
                    val totalItems = linearLayoutManager.itemCount
                    val scrolledOutItems = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                    if ((currentVisibleItem + scrolledOutItems) >= totalItems) {
                        isLoading = true
                        Toast.makeText(this@SecondActivity,"bottom",Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun setData() {
        lifecycleScope.launch(Dispatchers.IO) {
            ExploreDatabase.getInstance(this@SecondActivity).clearAllTables()
//            viewModel.getExploreData(page)
        }
        viewModel.getExploreData(page)

    }

    private fun getData() {
        viewModel.exploreLiveData.observe(this@SecondActivity, Observer {
            Log.d("exception", "observer: " + Thread.currentThread())
            exploreList.addAll(it.data)
            exploreAdapter.notifyDataSetChanged()

            isLoading = false
            lifecycleScope.launch(Dispatchers.IO) {
                for (item in it.data.indices) {
                    dao.insert(
                        Explore(
                            it.data.get(item).firstName,
                            it.data.get(item).lastName,
                            it.data.get(item).profilePicUrl,
                            it.data.get(item).uid
                        )
                    )
                }
            }

        })

        dao.getAllExploreData().observe(this, Observer {
            Log.d("explore", "" + it.size)
//            exploreList.addAll(it)
//            exploreAdapter.notifyDataSetChanged()
        })
    }

    private fun initialise() {
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        exploreAdapter = ExploreAdapter(this, exploreList, this)
        binding.recyclerView.adapter = exploreAdapter

        viewModel = ViewModelProvider(this).get(VerifyOtpViewModel::class.java)
        db = ExploreDatabase.getInstance(this)
        dao = db.exploreDao()
    }

    override fun getPosition(position: String) {
        Toast.makeText(this, "" + position, Toast.LENGTH_LONG).show()
    }


}