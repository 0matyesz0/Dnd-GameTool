package com.example.dnd_gametool_project.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dnd_gametool_project.R
import com.example.dnd_gametool_project.viewmodel.NewCharacterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_character_data.*


class CharacterDataFragment : Fragment() {
    private var newCharacterViewModel: NewCharacterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let{
            // we create the viewmodel in activity scope:
            newCharacterViewModel = ViewModelProviders.of(it).get(NewCharacterViewModel::class.java)
        }

        var races = arrayOf(
            resources.getString(R.string.dark_elf),
            resources.getString(R.string.dragonborn),
            resources.getString(R.string.dwarf),
            resources.getString(R.string.elf),
            resources.getString(R.string.forest_gnom),
            resources.getString(R.string.gnom),
            resources.getString(R.string.halfling),
            resources.getString(R.string.half_elf),
            resources.getString(R.string.half_orc),
            resources.getString(R.string.high_elf),
            resources.getString(R.string.hill_dwarf),
            resources.getString(R.string.human),
            resources.getString(R.string.lightfoot_halfling),
            resources.getString(R.string.mountain_dwarf),
            resources.getString(R.string.rock_gnom),
            resources.getString(R.string.stout_halfling)
        )
        val adapter = ArrayAdapter<String>(activity!!.applicationContext, android.R.layout.simple_spinner_item, races)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = view.findViewById<Spinner>(R.id.race_spinner)
        spinner.adapter = adapter
        spinner.prompt = resources.getString(R.string.raceSpinner)

        val button = activity!!.findViewById<FloatingActionButton>(R.id.fab_save)
        button.setOnClickListener {
            var fragment = CharacterAppearanceFragment()

            if (TextUtils.isEmpty(name_value.text.toString())) {
                showToastForNull(name_text.text.toString())
            } else if (TextUtils.isEmpty(armourClass_value.text.toString())) {
                showToastForNull(armourClass_text.text.toString())
            } else if (TextUtils.isEmpty(charisma_value.text.toString())) {
                showToastForNull(charisma_text.text.toString())
            } else if (TextUtils.isEmpty(constitution_value.text.toString())) {
                showToastForNull(constitution_text.text.toString())
            } else if (TextUtils.isEmpty(dexterity_value.text.toString())) {
                showToastForNull(dexterity_text.text.toString())
            } else if (TextUtils.isEmpty(experience_value.text.toString())) {
                showToastForNull(experience_text.text.toString())
            } else if (TextUtils.isEmpty(gold_value.text.toString())) {
                showToastForNull(gold_text.text.toString())
            } else if (TextUtils.isEmpty(hitDice_value.text.toString())) {
                showToastForNull(hitDice_text.text.toString())
            } else if (TextUtils.isEmpty(hp_value.text.toString())) {
                showToastForNull(hp_text.text.toString())
            } else if (TextUtils.isEmpty(initiative_value.text.toString())) {
                showToastForNull(initiative_text.text.toString())
            } else if (TextUtils.isEmpty(intelligence_value.text.toString())) {
                showToastForNull(intelligence_text.text.toString())
            } else if (TextUtils.isEmpty(level_value.text.toString())) {
                showToastForNull(level_text.text.toString())
            } else if (TextUtils.isEmpty(name_value.text.toString())) {
                showToastForNull(name_text.text.toString())
            } else if (TextUtils.isEmpty(perception_value.text.toString())) {
                showToastForNull(perception_text.text.toString())
            } else if (TextUtils.isEmpty(proficiency_value.text.toString())) {
                showToastForNull(proficiency_text.text.toString())
            } else if (TextUtils.isEmpty(speed_value.text.toString())) {
                showToastForNull(speed_text.text.toString())
            } else if (TextUtils.isEmpty(strength_value.text.toString())) {
                showToastForNull(strength_text.text.toString())
            } else {

                newCharacterViewModel?.name?.postValue(name_value.text.toString())
                newCharacterViewModel?.armourClass?.postValue(armourClass_value.text.toString().toInt())
                newCharacterViewModel?.charisma?.postValue(charisma_value.text.toString().toInt())
                newCharacterViewModel?.constitution?.postValue(constitution_value.text.toString().toInt())
                newCharacterViewModel?.dexterity?.postValue(dexterity_value.text.toString().toInt())
                newCharacterViewModel?.experience?.postValue(experience_value.text.toString().toInt())
                newCharacterViewModel?.gold?.postValue(gold_value.text.toString().toInt())
                newCharacterViewModel?.hitDice?.postValue(hitDice_value.text.toString().toInt())
                newCharacterViewModel?.healthPoints?.postValue(hp_value.text.toString().toInt())
                newCharacterViewModel?.initiative?.postValue(initiative_value.text.toString().toInt())
                newCharacterViewModel?.intelligence?.postValue(intelligence_value.text.toString().toInt())
                newCharacterViewModel?.level?.postValue(level_value.text.toString().toInt())
                newCharacterViewModel?.name?.postValue(name_value.text.toString())
                newCharacterViewModel?.perception?.postValue(perception_value.text.toString().toInt())
                newCharacterViewModel?.proficiency?.postValue(proficiency_value.text.toString().toInt())
                newCharacterViewModel?.race?.postValue(race_spinner.selectedItemPosition)
                newCharacterViewModel?.speed?.postValue(speed_value.text.toString().toInt())
                newCharacterViewModel?.strength?.postValue(strength_value.text.toString().toInt())
                newCharacterViewModel?.wisdom?.postValue(wisdom_value.text.toString().toInt())

                fragmentManager!!
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
        }
    }

    private fun showToastForNull(value: String){
        Toast.makeText(
            activity?.applicationContext,
            "You left the $value empty!",
            Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CharacterDataFragment()
    }
}
