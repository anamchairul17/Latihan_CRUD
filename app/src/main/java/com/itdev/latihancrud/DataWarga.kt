package com.itdev.latihancrud

data class DataWarga(
    val id : String,
    val nama: String,
    val umur : Int

){
    constructor(): this("", "", 0){

    }
}