package com.example.dnd_gametool_project

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.dnd_gametool_project.Dao.GameCharacterDao
import com.example.dnd_gametool_project.Dao.SpellDao
import com.example.dnd_gametool_project.database.DndDatabase
import com.example.dnd_gametool_project.model.GameCharacter
import com.example.dnd_gametool_project.model.Spell
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DndDatabaseTest{

    private lateinit var gameCharacterDao: GameCharacterDao
    private lateinit var spellDao: SpellDao
    private lateinit var db: DndDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //mock (fake) database entity:
        db = Room.inMemoryDatabaseBuilder(context, DndDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        gameCharacterDao = db.characterDao
        spellDao = db.spellDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetGameCharacter(){
        val gameCharacter = GameCharacter(-1)
        gameCharacterDao.insert(gameCharacter)
        val lastCharacter = gameCharacterDao.getLastCharacter()
        assertEquals(lastCharacter?.armourClass, 0)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetSpell(){
        val spell = Spell()
        spellDao.insert(spell)
        val lastSpell = spellDao.getLastSpell()
        assertEquals(lastSpell?.ritual, false)
    }

//    // child class of skill cannot be created without an owner character...
//    @Test(expected = SQLiteConstraintException::class)
//    fun insertAndGetSkillWithoutOwner(){
//        skillDao.insert(skill)
//        val lastSkill = skillDao.getLastSkill()
//    }
//
//    // child class creation after an owner class was created...
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetSkillWithOwner()
//    {
//        val gameCharacter = GameCharacter()
//        gameCharacterDao.insert(gameCharacter)
//        val lastCharacter = gameCharacterDao.getLastCharacter()
//        val skill = Skill()
//        skill.characterId = lastCharacter!!.characterId
//        skillDao.insert(skill)
//        val lastSkill = skillDao.getLastSkill()
//        assertEquals(lastSkill?.type, "")
//    }

}