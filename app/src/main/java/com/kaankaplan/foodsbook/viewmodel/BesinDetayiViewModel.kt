package com.kaankaplan.foodsbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaankaplan.foodsbook.model.Besin
import com.kaankaplan.foodsbook.service.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayiViewModel(application: Application) : BaseViewModel(application) {
    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid : Int){
        launch {
            val dao = BesinDatabase(getApplication()).BesinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }
}