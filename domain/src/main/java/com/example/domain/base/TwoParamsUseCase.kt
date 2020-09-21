package com.example.domain.base

import io.reactivex.rxjava3.core.Observable


interface TwoParamsUseCase<T: Any?, S: Any?, R: Any> {
    operator fun invoke(t: T, s: S): Observable<R>
}