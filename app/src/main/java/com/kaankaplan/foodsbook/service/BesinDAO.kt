package com.kaankaplan.foodsbook.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kaankaplan.foodsbook.model.Besin

@Dao
interface BesinDAO {

    // data access object

    @Insert
    suspend fun insertAll(vararg besin : Besin) : List<Long>

    //Insert -> Room, insert INTO
    //suspend -> coroutine scope
    //vararg -> more than 1 object
    //List<Long> long, ids

    @Query("SELECT * FROM besin")
    suspend fun getAllBesin() : List<Besin>

    @Query("SELECT * FROM besin WHERE uuid = :besinId")
    suspend fun getBesin(besinId : Int) : Besin

    @Query("DELETE FROM besin")
    suspend fun deleteAllBesin()
}