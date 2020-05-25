package com.example.dnd_gametool_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dnd_gametool_project.Dao.GameCharacterDao
import com.example.dnd_gametool_project.database.DndDatabase
import com.example.dnd_gametool_project.model.GameCharacter
import com.example.dnd_gametool_project.repository.GameCharacterRepository
import kotlinx.coroutines.*

class GameCharacterViewModel(application: Application) : AndroidViewModel(application){

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

    var characterDao = DndDatabase.getInstance(application, viewModelScope).characterDao // viewModelScope
    private val repository = GameCharacterRepository(characterDao)

    private var lastCharacter = MutableLiveData<GameCharacter?>()
    val characters = repository.characters


    // get character

    fun getCharacterById(id: Long) : GameCharacter?{
        var character : GameCharacter? = null

        viewModelScope.launch {
            character = getCharacterByIdFromDatabase(id)
        }
        return character
    }

    private suspend fun getCharacterByIdFromDatabase(id: Long) : GameCharacter?  {
        return withContext(Dispatchers.IO){
            repository.getCharacterById(id)
        }
    }

    // insert
    fun insert(character: GameCharacter) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(character)
    }

    // update
    fun update(character: GameCharacter) = viewModelScope.launch(Dispatchers.IO){
        repository.update(character)
    }
    // delete
    fun delete(id: Long) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteById(id)
    }

    // clear
    fun clear() = viewModelScope.launch(Dispatchers.IO){
        repository.clear()
    }
}