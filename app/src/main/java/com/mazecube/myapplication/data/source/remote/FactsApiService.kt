package com.mazecube.myapplication.data.source.remote

import androidx.lifecycle.LiveData
import com.mazecube.myapplication.data.ApiResponse
import com.mazecube.myapplication.data.model.Facts
import retrofit2.http.GET

interface FactsApiService {

    @GET("/facts/random")
    fun getData(): LiveData<ApiResponse<Facts>>
}