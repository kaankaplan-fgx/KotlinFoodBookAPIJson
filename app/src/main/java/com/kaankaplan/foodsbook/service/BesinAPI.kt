package com.kaankaplan.foodsbook.service

import com.kaankaplan.foodsbook.model.Besin
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface BesinAPI {
    //GET,POST

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    //BASE_URL -> MAİN WEBSİTE -> https://raw.githubusercontent.com/


    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin() : Single<List<Besin>>


}