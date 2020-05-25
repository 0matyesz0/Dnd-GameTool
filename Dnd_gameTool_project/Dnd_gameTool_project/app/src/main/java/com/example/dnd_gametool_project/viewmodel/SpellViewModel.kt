package com.example.dnd_gametool_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dnd_gametool_project.database.DndDatabase
import com.example.dnd_gametool_project.model.Spell
import com.example.dnd_gametool_project.repository.SpellRepository
import kotlinx.coroutines.*

class SpellViewModel(application: Application) : AndroidViewModel(application){
    // handles all coroutines
    private var viewModelJob = Job()

    // cancel all coroutines started from the viewmodel when it gets destroyed.
    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }

    // determining scope where the coroutines will run on.
    // Dispatchers.Main: coroutines in the uiScope will run on the main thread.
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var spellDao = DndDatabase.getInstance(application, viewModelScope).spellDao // viewModelScope
    private val repository = SpellRepository(spellDao)

    val spells = repository.spells

}