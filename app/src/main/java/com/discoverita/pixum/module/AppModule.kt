package com.discoverita.pixum.module

import com.discoverita.pixum.api.MarvelApi
import com.discoverita.pixum.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(Constants.AppModuleKey.API_KEY, Constants.PUBLIC_KEY)
                .addQueryParameter(Constants.AppModuleKey.TIMESTAMP, Constants.timeStamp)
                .addQueryParameter(Constants.AppModuleKey.HASH_KEY, Constants.hash())
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    @Provides
    @Singleton
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)
}