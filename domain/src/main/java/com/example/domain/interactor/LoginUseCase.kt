package com.example.domain.interactor

import com.example.domain.base.TwoParamsUseCase
import com.example.domain.isEmailValid
import com.example.domain.isPasswordValid
import com.example.domain.model.Result
import com.example.domain.repository.Repo
import io.reactivex.rxjava3.core.Observable

class LoginUseCase(private val repo: Repo): TwoParamsUseCase<String?, String?, Result> {
    override fun invoke(t: String?, s: String?): Observable<Result> {

        return if(isEmailValid(t) && isPasswordValid(s)){
            repo.loginUser(t.toString(), s.toString())
        } else{
            Observable.error(Throwable("Credentials error! Email or password is invalid"))
        }
    }
}