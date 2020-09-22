package com.example.applovertestapp.viewModel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.LoginUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LoginViewModel(private val loginUseCase: LoginUseCase): ViewModel() {

    private val disposables = CompositeDisposable()

    val emailObservableField = ObservableField<String>()
    val passwordObservableField = ObservableField<String>()

    private val isLoginStartedLiveData = MutableLiveData<Boolean>()
    private val successLiveData = MutableLiveData<String>()
    private val errorsLiveData = MutableLiveData<Throwable>()

    fun onLoginClicked(view: View){
        isLoginStartedLiveData.postValue(true)

        loginUseCase.invoke(emailObservableField.get(), passwordObservableField.get())
            .subscribe(
                { result ->
                    successLiveData.postValue(result.toString())
                    isLoginStartedLiveData.postValue(false)
                },
                { error ->
                    isLoginStartedLiveData.postValue(false)
                    errorsLiveData.postValue(error)
                } )
            .let { disposables.add(it) }

    }

    fun getSuccessLiveData() = successLiveData as LiveData<String>

    fun getErrorsLiveDate() = errorsLiveData as LiveData<Throwable>

    fun getIsLoginStartedLiveData() = isLoginStartedLiveData as LiveData<Boolean>

    fun clearDisposables(){
        disposables.clear()
    }

}