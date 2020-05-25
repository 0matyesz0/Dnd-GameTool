package com.example.dnd_gametool_project.repository

import androidx.lifecycle.LiveData
import com.example.dnd_gametool_project.Dao.SpellDao
import com.example.dnd_gametool_project.model.Spell

class SpellRepository(private val spellDao : SpellDao){

    val spells : LiveData<List<Spell>> = spellDao.getAllSpells()

}