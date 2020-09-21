package com.example.data.repository

import com.example.data.model.LoginRequest
import com.example.data.model.ResponsePojo
import com.example.domain.model.Result
import com.example.domain.repository.Repo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoImpl(private val remoteService: RemoteService): Repo {
    override fun loginUser(email: String, password: String): Observable<Result> {
        return remoteService.loginUser(LoginRequest(email, password))
            .subscribeOn(Schedulers.io())
            .map {
                Result(true)
            }
    }
}