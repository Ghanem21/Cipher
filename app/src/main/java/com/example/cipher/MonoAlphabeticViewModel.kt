package com.example.cipher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonoAlphabeticViewModel : ViewModel() {
    val plainText = MutableLiveData<String>()
    val cipherText = MutableLiveData<String>()

    private val planKey = "abcdefghijklmnopqrstuvwxyz "
    private val chainKey = "noatrbecfuxdqgylkhvijmpzsw "

    init {
        plainText.value = ""
        cipherText.value = ""
    }

    fun onEncrypt() {
        var str = ""
        plainText.value?.lowercase()?.forEach { char ->
            str += chainKey[planKey.indexOf(char)]
        }
        cipherText.value = str
    }

    fun onDecrypt() {
        var str = ""
        cipherText.value?.lowercase()?.forEach { char ->
            str += planKey[chainKey.indexOf(char)]
        }
        plainText.value = str
    }
}