package com.mazecube.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.mazecube.myapplication.data.Status
import com.mazecube.myapplication.databinding.ActivityMainBinding
import com.mazecube.myapplication.ui.common.DataAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding : ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = this.viewModel

        val adapter = DataAdapter(appExecutors, this.viewModel)
        dataBinding.recyclerView.adapter = adapter

        viewModel.data.observe(this) { data ->
//            Timber.e("viewModel.event.observe(this)")
            Timber.e("Status : {${data.status}}")
            Timber.e("Code : {${data.responseCode}}")
            Timber.e("ErrorMessage : {${data.message}}")

            //데이터 Get 성공한 것들만 데이터 추가
            if(data.status == Status.SUCCESS)
                data.data?.let { viewModel.addData(it) }
        }
    }
}