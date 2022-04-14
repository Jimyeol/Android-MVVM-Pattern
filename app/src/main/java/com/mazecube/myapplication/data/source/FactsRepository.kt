package com.mazecube.myapplication.data.source

import androidx.lifecycle.LiveData
import com.mazecube.myapplication.data.ApiResponse
import com.mazecube.myapplication.data.ApiSuccessResponse
import com.mazecube.myapplication.data.NetworkBoundResource
import com.mazecube.myapplication.data.Resource
import com.mazecube.myapplication.data.model.Facts
import com.mazecube.myapplication.data.source.remote.FactsApiService
import javax.inject.Inject

class FactsRepository @Inject constructor(
    private val factsApiService: FactsApiService
){
    fun getData(): LiveData<Resource<Facts>> {
        return object : NetworkBoundResource<Facts, Facts>() {
            override suspend fun processResponse(response: ApiSuccessResponse<Facts>): Facts {
                return response.body
            }

            override suspend fun createCall(): LiveData<ApiResponse<Facts>> {
                return factsApiService.getData()
            }
        }.asLiveData()
    }
}