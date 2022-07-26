package uz.pdp.dagger2nuntium.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.pdp.dagger2nuntium.network.ApiService
import uz.pdp.dagger2nuntium.utils.NetworkHelper
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"


    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun providesNetworkConnectivityHelper(context: Context): NetworkHelper {
        return NetworkHelper(context)
    }
}