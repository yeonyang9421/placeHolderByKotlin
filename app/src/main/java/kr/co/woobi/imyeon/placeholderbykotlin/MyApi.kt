package kr.co.woobi.imyeon.placeholderbykotlin

import io.reactivex.Observable
import retrofit2.http.GET

interface MyApi {
    @get:GET ("photos")
    val data: Observable<List<Model>>
}