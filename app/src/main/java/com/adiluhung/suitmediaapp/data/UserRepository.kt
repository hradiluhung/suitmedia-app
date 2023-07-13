package com.adiluhung.suitmediaapp.data

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.adiluhung.suitmediaapp.database.UserDatabase
import com.adiluhung.suitmediaapp.network.ApiService

class UserRepository(
    private val userDatabase: UserDatabase,
    private val apiService: ApiService
) {
    @OptIn(ExperimentalPagingApi::class)

    fun getUser() : LiveData<PagingData<User>>{

        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = UserRemoteMediator(userDatabase, apiService),
            pagingSourceFactory = {
                userDatabase.userDao().getAllUser()
            }
        ).liveData
    }
}