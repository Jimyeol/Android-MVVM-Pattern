package com.mazecube.myapplication.di

import com.mazecube.myapplication.data.source.remote.FactsApiService
import com.mazecube.myapplication.utill.LiveDataCallAdapterFactory
import com.mazecube.myapplication.BuildConfig
import com.orhanobut.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val OKHTTP_TAG = "OKHTTP"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor { message ->
            try {
                JSONObject(message)
                Logger.t(OKHTTP_TAG).json(message)
            } catch (error: JSONException) {
                if (message.contains("http"))
                    Logger.t(OKHTTP_TAG).d(message)
            }
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApiService(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl("https://cat-fact.herokuapp.com")
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FactsApiService =
        retrofit.create(FactsApiService::class.java)
}