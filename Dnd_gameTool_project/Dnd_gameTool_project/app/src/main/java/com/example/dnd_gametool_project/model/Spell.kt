package com.example.dnd_gametool_project.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dnd_gametool_project.util.ListStringConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "spell_table")
data class Spell(

    @PrimaryKey(autoGenerate = true) var spellId: Long = 0,

    var name: String = "",

    var level: String = "",

    var school: String = "",

    var castingTime: String = "",

    var duration: String = "",

    var components: String = "",

    var material: Boolean = false,

    var somatic: Boolean = false,

    var verbal: Boolean = false,

    var range: String = "",

    var description: String = "",

    var classes: String = "",

    var tags: String = "",

    var type: String = "",

    var ritual: Boolean = false
) : Parcelable