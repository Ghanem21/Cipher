package com.example.cipher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayFairViewModel : ViewModel() {
    val plainText = MutableLiveData<String>()
    val cipherText = MutableLiveData<String>()
    val key = MutableLiveData<String>()
    val dialog = MutableLiveData<Boolean>()

    private var planKey = "abcdefghijklmnopqrstuvwxyz"

    init {
        plainText.value = ""
        cipherText.value = ""
        key.value = "Monarchy"
        dialog.value = false
    }

    fun onEncrypt(){
        try {
            val playFair = Play(key.value, plainText.value)
            playFair.cleanPlayFairKey()
            playFair.generateCipherKey()
            if (plainText.value!!.length % 2 == 0) {
                cipherText.value = playFair.encryptMessage()
            } else {
                showDialog()
            }
        }catch (ex:java.lang.Exception){
            showDialog()
        }

    }

    private fun showDialog() {
        dialog.value = true
    }

    fun doneShowDialog(){
        dialog.value = false
    }

    fun onDecrypt(){
        try {
            val playFair = Play(key.value, cipherText.value)
            playFair.cleanPlayFairKey()
            playFair.generateCipherKey()
            if (cipherText.value!!.length % 2 == 0) {
                plainText.value = playFair.encryptMessage()
            } else {
                showDialog()
            }
        }catch (ex:java.lang.Exception){
            showDialog()
        }
    }


}

