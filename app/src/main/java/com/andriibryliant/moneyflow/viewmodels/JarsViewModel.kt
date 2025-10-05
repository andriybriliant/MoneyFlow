package com.andriibryliant.moneyflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriibryliant.moneyflow.objects.Jar
import com.andriibryliant.moneyflow.objects.JarType

class JarsViewModel : ViewModel() {
    private val _jars = MutableLiveData<List<Jar>>()

    val jarsList: LiveData<List<Jar>> = _jars

    init{
        fetchJars()
    }

    fun fetchJars(){
        loadDummyData()
    }

    fun loadDummyData(){
        _jars.value = listOf(
            Jar("Laptop", 1500.0, 450.50, JarType.DEFAULT, "", 0.0, "$"),
            Jar("Car", 45000.0, 15000.0, JarType.RECURRING, "1 month", 500.0, "€"),
            Jar("Camera", 700.0, 300.0, JarType.DEFAULT, "", 0.0, "₴"),
            Jar("Home", 130000.0, 55000.0, JarType.RECURRING, "1 month", 1500.0, "zł")
        )
    }
}