package com.adiluhung.suitmediaapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adiluhung.suitmediaapp.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<User>)

    @Query("SELECT * FROM user")
    fun getAllUser(): PagingSource<Int, User>

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}