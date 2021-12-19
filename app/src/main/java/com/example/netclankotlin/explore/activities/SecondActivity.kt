package com.example.netclankotlin.explore.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.netclankotlin.R
import com.example.netclankotlin.databinding.ActivitySecondBinding
import com.example.netclankotlin.explore.Data
import com.example.netclankotlin.explore.adapters.ExploreAdapter
import com.example.netclankotlin.otp.VerifyOtpViewModel
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.netclankotlin.room.ExploreDao
import com.example.netclankotlin.room.Note
import com.example.netclankotlin.room.NoteDatabase
import kotlinx.coroutines.launch


class SecondActivity : AppCompatActivity(), ExploreAdapter.Callback {
    lateinit var binding: ActivitySecondBinding
    lateinit var exploreAdapter: ExploreAdapter
    val exploreList = ArrayList<Note>()
    lateinit var viewModel: VerifyOtpViewModel
    lateinit var  dao:ExploreDao
    lateinit var db:NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        viewModel = ViewModelProvider(this).get(VerifyOtpViewModel::class.java)
        db = NoteDatabase.getInstance(this)
        dao = db.exploreDao()
        lifecycleScope.launch {
            viewModel.getExploreData(1)
        }
//        runBlocking {
//            launch {
//                viewModel.getExploreData(1)
//            }
//        }


        getData()
        initialise()
    }

    private fun getData() {
        viewModel.exploreLiveData.observe(this@SecondActivity, Observer {
            Log.d("exception", "observer: " + Thread.currentThread())
            for (item in it.data.indices){
                dao.insert(Note(it.data.get(item).firstName,it.data.get(item).lastName,it.data.get(item).profilePicUrl,it.data.get(item).uid))
            }


        })



        dao.getAllNotes().observe(this, Observer {
            Log.d("explore",""+it.size)
            exploreList.addAll(it)
            exploreAdapter.notifyDataSetChanged()
        })
    }

    private fun initialise() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        exploreAdapter = ExploreAdapter(this, exploreList,this)
        binding.recyclerView.adapter = exploreAdapter
    }

    override fun getPosition(position:String) {
        Toast.makeText(this, ""+position, Toast.LENGTH_LONG).show()
    }


}