package com.example.dnd_gametool_project.repository

import androidx.lifecycle.LiveData
import com.example.dnd_gametool_project.Dao.GameCharacterDao
import com.example.dnd_gametool_project.database.DndDatabase
import com.example.dnd_gametool_project.model.GameCharacter

class GameCharacterRepository(private val characterDao : GameCharacterDao){

    val characters : LiveData<List<GameCharacter>> = characterDao.getCharacters()

    suspend fun getCharacterById(id: Long) : GameCharacter?{
        return characterDao.get(id)
    }

    suspend fun insert(gameCharacter : GameCharacter){
        characterDao.insert(gameCharacter)
    }

    suspend fun update(gameCharacter: GameCharacter){
        characterDao.update(gameCharacter)
    }

    suspend fun clear(){
        characterDao.clear()
    }

    suspend fun deleteById(id: Long){
        characterDao.delete(id)
    }

    suspend fun getLastCharacter(): GameCharacter? {
        return characterDao.getLastCharacter()
    }
}