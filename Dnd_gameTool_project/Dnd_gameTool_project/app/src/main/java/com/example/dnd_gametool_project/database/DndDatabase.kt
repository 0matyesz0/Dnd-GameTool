package com.example.dnd_gametool_project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dnd_gametool_project.Dao.GameCharacterDao
import com.example.dnd_gametool_project.Dao.SpellDao
import com.example.dnd_gametool_project.R
import com.example.dnd_gametool_project.model.GameCharacter
import com.example.dnd_gametool_project.model.Spell
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.util.*


@Database(entities = arrayOf(GameCharacter::class, Spell::class), version = 4, exportSchema = false)
abstract class DndDatabase : RoomDatabase() {

    abstract val characterDao: GameCharacterDao
    abstract val spellDao: SpellDao
    //abstract val skillDao : SkillDao

    // there's no need to instantiate the class so we use the singleton pattern.
    companion object {

        // Volatile: every change of the database will force all threads to update, so there won't be any conflict between the threads.
        // INSTANCE: this keeps the reference to the database once it is aquired.
        @Volatile
        private var INSTANCE: DndDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): DndDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // only one thread can have access to the database in the critical block.
            synchronized(this) {
                // create database with a builder.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DndDatabase::class.java,
                    "dnd_database"
                )
                    .fallbackToDestructiveMigration()  // when using the app at another environment, our schema should be migrated. but due to the lack of complexity of the app, i simply destroy and rebuild the database schema.
                    .addCallback(PrePopulateCallback(scope, context))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class PrePopulateCallback(
        private val scope: CoroutineScope,
        private val context: Context
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val spellDao = database.spellDao

                    val ins: InputStream = context.resources.openRawResource(R.raw.spells)
                    val scanner = Scanner(ins)
                    val builder = StringBuilder()
                    while (scanner.hasNextLine()) {
                        builder.append(scanner.nextLine())
                    }

                    val spells = JSONArray(builder.toString())
                    for (i in 0 until spells.length()) {
                        val castingTime = spells.getJSONObject(i).opt("casting_time")?.toString()
                        val classes = spells.getJSONObject(i).opt("classes") as JSONArray
                        var classesVal = ""
                        for (i in 0 until classes.length()) {
                            classesVal += classes.get(i).toString() + ","
                        }
                        var componentsVal = ""
                        val components = spells.getJSONObject(i).getJSONObject("components")
                        val material = components.opt("material")?.toString()?.toBoolean()
                        val raw = components.opt("raw")?.toString()
                        val somatic = components.opt("somatic")?.toString()?.toBoolean()
                        val verbal = components.opt("verbal")?.toString()?.toBoolean()
                        //componentsVal += material
                        componentsVal += raw
                        //componentsVal += somatic
                        //componentsVal += verbal
                        val description = spells.getJSONObject(i).opt("description")?.toString()
                        val duration = spells.getJSONObject(i).opt("duration")?.toString()
                        val level = spells.getJSONObject(i).opt("level")?.toString()
                        val name = spells.getJSONObject(i).opt("name")?.toString()
                        val range = spells.getJSONObject(i).opt("range")?.toString()
                        val ritual = spells.getJSONObject(i).opt("ritual")?.toString()?.toBoolean()
                        val school = spells.getJSONObject(i).opt("school")?.toString()
                        val tags = spells.getJSONObject(i).opt("tags") as JSONArray
                        var tagsVal = ""
                        for (i in 0 until tags.length()) {
                            tagsVal += tags.get(i).toString() + ","
                        }
                        val type = spells.getJSONObject(i).opt("type")?.toString()

                        val spell = Spell(
                            0,
                            name!!,
                            level!!,
                            school!!,
                            castingTime!!,
                            duration!!,
                            componentsVal,
                            material!!,
                            somatic!!,
                            verbal!!,
                            range!!,
                            description!!,
                            classesVal,
                            tagsVal,
                            type!!,
                            ritual!!
                        )

                        spellDao.insert(spell)
                    }
                }
            }
        }
    }
}