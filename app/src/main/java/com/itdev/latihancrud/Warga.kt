package com.itdev.latihancrud

data class Warga(
    val id : String,
    val nama: String,
    val asal : String

){
    constructor(): this("", "", ""){

    }
}