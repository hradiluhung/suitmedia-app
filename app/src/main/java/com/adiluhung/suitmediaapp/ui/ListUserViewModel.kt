package com.adiluhung.suitmediaapp.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.adiluhung.suitmediaapp.data.User
import com.adiluhung.suitmediaapp.data.UserRepository
import com.adiluhung.suitmediaapp.di.Injection

class ListUserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val users : LiveData<PagingData<User>> = userRepository.getUser().cachedIn(viewModelScope)
}

class ViewModelFactory(private val context : Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListUserViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ListUserViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}