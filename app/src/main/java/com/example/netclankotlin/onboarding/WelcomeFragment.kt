package com.example.netclankotlin.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.netclankotlin.R
import com.example.netclankotlin.explore.activities.SecondActivity
import com.example.netclankotlin.explore.activities.ThirdActivity
import com.example.netclankotlin.databinding.FragmentWelcomeBinding
import com.example.netclankotlin.network.RequestFormatter
import com.example.netclankotlin.otp.VerifyOtpViewModel
import com.example.netclankotlin.utils.PrefManager

class WelcomeFragment : Fragment() {
    lateinit var binding: FragmentWelcomeBinding
    lateinit var getOtpViewModel: VerifyOtpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome, container, false)
        initialiseView()
        networkCalls()
        binding.startedBtn.setOnClickListener {
            startActivity(Intent(context,SecondActivity::class.java))
        }
        return binding.root
    }

    private fun networkCalls() {
        RequestFormatter.jsonObjectGetOtp("7005667189","91",0)?.let { getOtpViewModel.getOtpViewModel(it) }
        getOtpViewModel.getOtpLiveData.observe(viewLifecycleOwner, Observer {
            PrefManager.getInstance(this.requireActivity()).sessionId = it.sessionId

            RequestFormatter.jsonObjectVerifyOtp("7005667189","91", PrefManager.getInstance(this.requireActivity()).sessionId)
                ?.let { it1 -> getOtpViewModel.verifyOtpViewModel(it1) }
        })


        getOtpViewModel.verifyOtpLiveData.observe(viewLifecycleOwner, Observer {

            PrefManager.getInstance(this.requireActivity()).authKey = it.authKey
//            Log.d("Explore","auth: "+PrefManager.getInstance(this.requireActivity()).authKey)

            startActivity(Intent(context, SecondActivity::class.java))
        })
    }

    private fun initialiseView() {
        getOtpViewModel = ViewModelProvider(this).get(VerifyOtpViewModel::class.java)
    }



}