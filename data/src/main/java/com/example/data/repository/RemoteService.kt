package com.example.data.repository

import com.example.data.model.LoginRequest
import com.example.data.model.ResponsePojo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteService {

    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest) : Observable<ResponsePojo>

}