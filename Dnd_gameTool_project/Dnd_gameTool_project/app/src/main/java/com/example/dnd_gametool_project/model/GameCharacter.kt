package com.example.dnd_gametool_project.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "game_character_table")
data class GameCharacter (

    @PrimaryKey(autoGenerate = true) val characterId: Long,

    @ColumnInfo(name = "armour_class") val armourClass: Int = 0,

    @ColumnInfo(name = "character_class") val character_class: Int = 0,

    @ColumnInfo(name = "charisma") val charisma: Int = 0,

    @ColumnInfo(name = "constitution") val constitution: Int = 0,

    @ColumnInfo(name = "dexterity") val dexterity: Int = 0,

    @ColumnInfo(name = "experience") val experience: Int = 0,

    @ColumnInfo(name = "gold") val gold : Int = 0,

    @ColumnInfo(name = "hitDice") val hitDice: Int = 0,

    @ColumnInfo(name = "health_points") val healthPoints: Int = 0,

    @ColumnInfo(name = "initiative") val initiative: Int = 0,

    @ColumnInfo(name = "intelligence") val intelligence: Int = 0,

    @ColumnInfo(name = "level") val level: Int = 0,

    @ColumnInfo(name = "name") val name: String = "",

    @ColumnInfo(name = "perception") val perception: Int = 0,

    @ColumnInfo(name = "proficiency") val proficiency: Int = 0,

    @ColumnInfo(name = "race") val race: Int = 0,

    //@ColumnInfo(name = "skills")
    //var skills: HashMap<String, Int?>,

    @ColumnInfo(name = "speed") val speed: Int = 0,

    @ColumnInfo(name = "strength") val strength: Int = 0,

    @ColumnInfo(name = "wisdom") val wisdom: Int = 0
) : Parcelable