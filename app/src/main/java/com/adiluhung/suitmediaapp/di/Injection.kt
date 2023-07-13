package com.adiluhung.suitmediaapp.di

import android.content.Context
import com.adiluhung.suitmediaapp.data.UserRepository
import com.adiluhung.suitmediaapp.database.UserDatabase
import com.adiluhung.suitmediaapp.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository(database, apiService)
    }
}