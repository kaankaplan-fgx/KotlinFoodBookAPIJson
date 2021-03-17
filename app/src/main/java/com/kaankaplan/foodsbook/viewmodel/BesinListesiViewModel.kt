package com.kaankaplan.foodsbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaankaplan.foodsbook.model.Besin
import com.kaankaplan.foodsbook.service.BesinAPIService
import com.kaankaplan.foodsbook.service.BesinDatabase
import com.kaankaplan.foodsbook.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BesinListesiViewModel(application: Application) : BaseViewModel(application) {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji = MutableLiveData<Boolean>()
    val besinProgressBar = MutableLiveData<Boolean>()
    private var guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    private val besinAPIServis = BesinAPIService()
    private val disposable = CompositeDisposable()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())


    fun refreshData(){

        val keydedilmeZamani = ozelSharedPreferences.zamaniAl()

        if (keydedilmeZamani != null && keydedilmeZamani != 0L && System.nanoTime() - keydedilmeZamani < guncellemeZamani){
            verileriSqlitedenAl()
        }else{
            verileriInternettenAl()
        }
    }

    private fun verileriSqlitedenAl(){
        besinProgressBar.value = true

        launch {
            val besinListesi = BesinDatabase(getApplication()).BesinDao().getAllBesin()
            besinleriGoster(besinListesi)


        }

    }

    fun refreshFromInternet(){
        verileriInternettenAl()
    }

    private fun verileriInternettenAl(){
        besinProgressBar.value = true

        //IO,Default, UI -> Thread Pool
        disposable.add(
                besinAPIServis.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Besin>>(){
                    override fun onSuccess(t: List<Besin>) {
                        //BAŞARILI OLURSA
                        sqLiteSakla(t)

                    }

                    override fun onError(e: Throwable) {
                        //HATA OLURSA
                        besinHataMesaji.value = true
                        besinProgressBar.value = false
                        e.printStackTrace()

                    }

                })

        )
    }

    private fun besinleriGoster(besinlerListesi : List<Besin>){
        besinler.value = besinlerListesi
        besinHataMesaji.value = false
        besinProgressBar.value = false
    }
    private fun sqLiteSakla(besinListesi : List<Besin>){
        launch {
            val dao = BesinDatabase(getApplication()).BesinDao()
            dao.deleteAllBesin()
            val uuidListesi = dao.insertAll(*besinListesi.toTypedArray())
            var i = 0
            while (i < besinListesi.size){
                besinListesi[i].uuid = uuidListesi[i].toInt()
                i += 1
            }
            besinleriGoster(besinListesi)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}