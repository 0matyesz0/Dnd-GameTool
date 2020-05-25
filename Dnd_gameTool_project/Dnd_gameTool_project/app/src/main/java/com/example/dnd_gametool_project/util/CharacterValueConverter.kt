package com.example.dnd_gametool_project.util

import android.content.Context
import com.example.dnd_gametool_project.R

class CharacterValueConverter(context: Context){

    companion object {

        fun convertToRace(value: Int, context: Context): String {
            return when (value) {
                0 -> context.resources.getString(R.string.dark_elf)
                1 -> context.resources.getString(R.string.dragonborn)
                2 -> context.resources.getString(R.string.dwarf)
                3 -> context.resources.getString(R.string.elf)
                4 -> context.resources.getString(R.string.forest_gnom)
                5 -> context.resources.getString(R.string.gnom)
                6 -> context.resources.getString(R.string.halfling)
                7 -> context.resources.getString(R.string.half_elf)
                8 -> context.resources.getString(R.string.half_orc)
                9 -> context.resources.getString(R.string.high_elf)
                10 -> context.resources.getString(R.string.hill_dwarf)
                11 -> context.resources.getString(R.string.human)
                12 -> context.resources.getString(R.string.lightfoot_halfling)
                13 -> context.resources.getString(R.string.mountain_dwarf)
                14 -> context.resources.getString(R.string.rock_gnom)
                else -> context.resources.getString(R.string.stout_halfling)
            }
        }

        fun convertToClass(value: Int, context: Context): String {
            return when (value) {
                0 -> context.resources.getString(R.string.barbarian)
                1 -> context.resources.getString(R.string.bard)
                2 -> context.resources.getString(R.string.cleric)
                3 -> context.resources.getString(R.string.druid)
                4 -> context.resources.getString(R.string.fighter)
                5 -> context.resources.getString(R.string.monk)
                6 -> context.resources.getString(R.string.paladin)
                7 -> context.resources.getString(R.string.ranger)
                8 -> context.resources.getString(R.string.rogue)
                9 -> context.resources.getString(R.string.sorcerer)
                10 -> context.resources.getString(R.string.warlock)
                else -> context.resources.getString(R.string.wizard)
            }
        }

        fun getExperienceLeftToLevelUp(level: Int, exp: Int) : Int
        {
            return when(level){
                1 -> 300 - exp
                2 -> 900 - exp
                3 -> 2700 - exp
                4 -> 6500 - exp
                5 -> 14000 - exp
                6 -> 23000 - exp
                7 -> 34000 - exp
                8 -> 48000 - exp
                9 -> 64000 - exp
                10 -> 85000 - exp
                11 -> 100000 - exp
                12 -> 120000 - exp
                13 -> 140000 - exp
                14 -> 165000 - exp
                15 -> 195000 - exp
                16 -> 225000 - exp
                17 -> 265000 - exp
                18 -> 305000 - exp
                19 -> 355000 - exp
                else -> 0
            }
        }

        fun getLevelFromGivenEcperience(exp: Int) : Int
        {
            return when(exp){
                in 1..299 -> 1
                in 300..899 -> 2
                in 900..2699 -> 3
                in 2700..6499 -> 4
                in 6500..13999 -> 5
                in 14000..22999 -> 6
                in 23000..33999 -> 7
                in 34000..47999 -> 8
                in 48000..63999 -> 9
                in 64000..84999 -> 10
                in 85000..99999 -> 11
                in 100000..119999 -> 12
                in 120000..139999 -> 13
                in 140000..164999 -> 14
                in 165000..194999 -> 15
                in 195000..224999 -> 16
                in 225000..264999 -> 17
                in 265000..304999 -> 18
                in 305000..354999 -> 19
                else -> 20
            }
        }

        fun convertToClassImage(value: Int): Int {
            return when (value) {
                0 -> R.drawable.barbarian
                1 -> R.drawable.bard
                2 -> R.drawable.cleric
                3 -> R.drawable.druid
                4 -> R.drawable.fighter
                5 -> R.drawable.monk
                6 -> R.drawable.paladin
                7 -> R.drawable.ranger
                8 -> R.drawable.rogue
                9 -> R.drawable.sorcerer
                10 -> R.drawable.warlock
                else -> R.drawable.wizard
            }
        }
    }
}