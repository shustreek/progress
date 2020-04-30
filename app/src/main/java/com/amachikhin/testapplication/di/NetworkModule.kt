package com.amachikhin.testapplication.di

import com.amachikhin.testapplication.BuildConfig
import com.amachikhin.testapplication.api.Api
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://openweathermap.org/data/2.5/"
const val OKHTTP_CONNECT_TIMEOUT = 10L
const val OKHTTP_WRITE_TIMEOUT = 60L
const val OKHTTP_READ_TIMEOUT = 60L

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(gson: Gson): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideHttpClient())
            .build().create(Api::class.java)
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain
                    .request().run {
                        val url = url()
                            .newBuilder()
                            .addQueryParameter("appid", "439d4b804bc8187953eb36d2a8c26a02")
                            .build()

                        newBuilder()
                            .url(url)
                            .build()
                    }
                )
            }
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OKHTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                        addInterceptor(this)
                    }
                }
            }
            .build()
    }


}
