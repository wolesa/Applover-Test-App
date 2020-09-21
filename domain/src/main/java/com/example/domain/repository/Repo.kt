package com.example.domain.repository

import com.example.domain.model.Result
import io.reactivex.rxjava3.core.Observable

interface Repo {

    fun loginUser(email: String, password: String): Observable<Result>

}