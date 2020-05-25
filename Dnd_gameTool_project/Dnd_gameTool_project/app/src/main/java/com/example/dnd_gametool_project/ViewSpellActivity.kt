package com.example.dnd_gametool_project

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.dnd_gametool_project.model.Spell
import kotlinx.android.synthetic.main.activity_view_spell.*


class ViewSpellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_spell)

        val currentSpell = intent?.getParcelableExtra<Spell>("gameSpell")

        // setting the textViews:
        spellNameText.text = currentSpell?.name
        spellLevelText.text = "level " + currentSpell?.level
        spellSchoolText.text = currentSpell?.school
        spellCastingTimeText.text = currentSpell?.castingTime
        spellRangeText.text = currentSpell?.range
        spellTypeText.text = currentSpell?.type
        spellDurationText.text = currentSpell?.duration
        spellComponentsVal.text = currentSpell?.components
        spellDescriptionVal.text = currentSpell?.description
        spellDescriptionVal.movementMethod = ScrollingMovementMethod()
    }
}
