package com.example.dnd_gametool_project.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dnd_gametool_project.model.GameCharacter

@Dao
interface GameCharacterDao {

    @Insert
    fun insert(character: GameCharacter)

    @Update
    fun update(character: GameCharacter)

    @Query("SELECT * FROM game_character_table WHERE characterId = :key")
    fun get(key: Long): GameCharacter?

    @Query("DELETE FROM game_character_table")
    fun clear()

    @Query("DELETE FROM game_character_table WHERE characterId = :key")
    fun delete(key: Long)

    @Query("SELECT * FROM game_character_table ORDER BY characterId DESC")
    fun getCharacters(): LiveData<List<GameCharacter>>

    @Query("SELECT * FROM game_character_table ORDER BY characterId DESC LIMIT 1")
    fun getLastCharacter(): GameCharacter?
}