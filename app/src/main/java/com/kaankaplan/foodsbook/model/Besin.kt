package com.kaankaplan.foodsbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Besin(
        @ColumnInfo(name = "isim")
        @SerializedName("isim")
    val BesinIsim: String?,
        @ColumnInfo(name = "kalori")
        @SerializedName("kalori")
    val BesinKalori : String?,
        @ColumnInfo(name = "karbonhidrat")
        @SerializedName("karbonhidrat")
    val BesinKarbonhidrat : String?,
        @ColumnInfo(name = "protein")
        @SerializedName("protein")
    val BesinProtein : String?,
        @ColumnInfo(name = "yag")
        @SerializedName("yag")
    val BesinYag : String?,
        @ColumnInfo(name = "gorsel")
        @SerializedName("gorsel")
    val besinGorsel : String?
    ) {

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}