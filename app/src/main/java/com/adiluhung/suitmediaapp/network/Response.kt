package com.adiluhung.suitmediaapp.network

import com.adiluhung.suitmediaapp.data.User
import com.google.gson.annotations.SerializedName

data class Response(
	@field:SerializedName("data")
	val data: List<User>
)
