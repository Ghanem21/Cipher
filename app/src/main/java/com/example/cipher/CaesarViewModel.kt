package com.example.cipher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CaesarViewModel : ViewModel() {
    val plainText = MutableLiveData<String>()
    val cipherText = MutableLiveData<String>()
    val key = MutableLiveData<String>()

    private val planKey = "abcdefghijklmnopqrstuvwxyz"

    init {
        plainText.value = ""
        cipherText.value = ""
        key.value = "0"
    }

    fun onEncrypt(){
        var str = ""
        val add = key.value?.toInt() ?: 0
        plainText.value?.lowercase()?.forEach {
            char ->
            str += planKey[((char.code - 'a'.code) + add) % 26]
        }
        cipherText.value = str
    }

    fun onDecrypt(){
        var str = ""
        val add = key.value?.toInt() ?: 0
        cipherText.value?.lowercase()?.forEach {
                char ->
            str += planKey[((char.code - 'a'.code) - add + 26 * add) % 26]
        }
        plainText.value = str
    }
}