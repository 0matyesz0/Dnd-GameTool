package com.example.dnd_gametool_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class NewCharacterViewModel(application: Application) : AndroidViewModel(application) {

    
	val armourClass= MutableLiveData<Int>()
    val character_class= MutableLiveData<Int>()
    val charisma= MutableLiveData<Int>()
    val constitution= MutableLiveData<Int>()
    val dexterity= MutableLiveData<Int>()
    val experience= MutableLiveData<Int>()
    val gold= MutableLiveData<Int>()
    val hitDice= MutableLiveData<Int>()
    val healthPoints= MutableLiveData<Int>()
    val initiative= MutableLiveData<Int>()
    val intelligence= MutableLiveData<Int>()
    val level= MutableLiveData<Int>()
    val name= MutableLiveData<String>()
    val perception= MutableLiveData<Int>()
    val proficiency= MutableLiveData<Int>()
    val race= MutableLiveData<Int>()
    val speed= MutableLiveData<Int>()
    val strength= MutableLiveData<Int>()
    val wisdom= MutableLiveData<Int>()

}