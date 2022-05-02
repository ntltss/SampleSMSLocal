package com.example.samplesmslocal.ui.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samplesmslocal.MainActivity

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        //"Hello world from section: $it"
        if(_index.value==1){
            //return "TAB1の内容: " ;
            "TAB11"

        } else{
            //return "TAB2の内容: " ;
            "TAB22"
        }

    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}