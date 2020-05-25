package com.example.dnd_gametool_project.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dnd_gametool_project.model.Spell

@Dao
interface SpellDao {

    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllSpells(spells: List<Spell>)*/

    @Insert
    fun insert(spell: Spell)

    @Update
    fun update(spell: Spell)

    @Query("SELECT * FROM spell_table WHERE spellId = :key")
    fun get(key: Long): Spell

    @Query("DELETE FROM spell_table")
    fun clear()

    @Query("SELECT * FROM spell_table ORDER BY spellId DESC")
    fun getAllSpells(): LiveData<List<Spell>>

    @Query("SELECT * FROM spell_table ORDER BY spellId DESC LIMIT 1")
    fun getLastSpell(): Spell?
}