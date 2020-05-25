package com.example.dnd_gametool_project.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.dnd_gametool_project.R
import com.example.dnd_gametool_project.util.CharacterValueConverter
import com.example.dnd_gametool_project.viewmodel.NewCharacterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_character_appearance.*
import kotlinx.android.synthetic.main.fragment_character_data.*


class CharacterAppearanceFragment : Fragment() {

    lateinit var backgroundAnimation: AnimationDrawable
    private var newCharacterViewModel: NewCharacterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_character_appearance, container, false)

        var layout = view.findViewById<LinearLayout>(R.id.appearance_layout)
        layout.setBackgroundResource(R.drawable.background_animation)
        backgroundAnimation = layout.background as AnimationDrawable
        backgroundAnimation.start()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            // we create the viewmodel in activity scope:
            newCharacterViewModel = ViewModelProviders.of(it).get(NewCharacterViewModel::class.java)
        }
        barbar_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(0)
            changeSelection(0)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(0, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        bard_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(1)
            changeSelection(1)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(1, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        cleric_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(2)
            changeSelection(2)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(2, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        druid_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(3)
            changeSelection(3)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(3, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        fighter_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(4)
            changeSelection(4)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(4, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        monk_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(5)
            changeSelection(5)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(5, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        paladin_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(6)
            changeSelection(6)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(6, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        ranger_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(7)
            changeSelection(7)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(7, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        rogue_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(8)
            changeSelection(8)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(8, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        sorcerer_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(9)
            changeSelection(9)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(9, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        warlock_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(10)
            changeSelection(10)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(10, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }
        wizard_img.setOnClickListener {
            newCharacterViewModel?.character_class?.postValue(11)
            changeSelection(11)
            Toast.makeText(
                activity,
                CharacterValueConverter.convertToClass(11, activity!!.applicationContext),
                Toast.LENGTH_LONG
            ).show()
        }

        val button = activity!!.findViewById<FloatingActionButton>(R.id.fab_save)
        button.setOnClickListener {
            if (newCharacterViewModel?.character_class?.value != null) {
                val replyIntent = Intent()
                val nameValue = newCharacterViewModel?.name
                val armourClass = newCharacterViewModel?.armourClass
                val characterClass = newCharacterViewModel?.character_class
                val charisma = newCharacterViewModel?.charisma
                val constitution = newCharacterViewModel?.constitution
                val dexterity = newCharacterViewModel?.dexterity
                val experience = newCharacterViewModel?.experience
                val gold = newCharacterViewModel?.gold
                val hitDice = newCharacterViewModel?.hitDice
                val healthPoints = newCharacterViewModel?.healthPoints
                val initiative = newCharacterViewModel?.initiative
                val intelligence = newCharacterViewModel?.intelligence
                val level = newCharacterViewModel?.level
                val name = newCharacterViewModel?.name
                val perception = newCharacterViewModel?.perception
                val proficiency = newCharacterViewModel?.proficiency
                val race = newCharacterViewModel?.race
                val speed = newCharacterViewModel?.speed
                val strength = newCharacterViewModel?.strength
                val wisdom = newCharacterViewModel?.wisdom

                replyIntent.putExtra("character_name", nameValue?.value.toString())
                replyIntent.putExtra("armour_class", armourClass?.value.toString())
                replyIntent.putExtra("character_class", characterClass?.value.toString())
                replyIntent.putExtra("charisma", charisma?.value.toString())
                replyIntent.putExtra("constitution", constitution?.value.toString())
                replyIntent.putExtra("dexterity", dexterity?.value.toString())
                replyIntent.putExtra("experience", experience?.value.toString())
                replyIntent.putExtra("gold", gold?.value.toString())
                replyIntent.putExtra("hit_dice", hitDice?.value.toString())
                replyIntent.putExtra("hp", healthPoints?.value.toString())
                replyIntent.putExtra("initiative", initiative?.value.toString())
                replyIntent.putExtra("intelligence", intelligence?.value.toString())
                replyIntent.putExtra("level", level?.value.toString())
                replyIntent.putExtra("name", name?.value.toString())
                replyIntent.putExtra("perception", perception?.value.toString())
                replyIntent.putExtra("proficiency", proficiency?.value.toString())
                replyIntent.putExtra("race", race?.value.toString())
                replyIntent.putExtra("speed", speed?.value.toString())
                replyIntent.putExtra("strength", strength?.value.toString())
                replyIntent.putExtra("wisdom", wisdom?.value.toString())
                activity!!.setResult(Activity.RESULT_OK, replyIntent)
                activity!!.finish()
            }
            else{
                Toast.makeText(
                    context,
                    "You need to select a class first!",
                    Toast.LENGTH_LONG
                ).show()
            }
            }
        }



    private fun changeSelection (pos: Int){
        barbar_selection.visibility = if (pos == 0) View.VISIBLE else View.GONE
        bard_selection.visibility = if (pos == 1) View.VISIBLE else View.GONE
        cleric_selection.visibility = if (pos == 2) View.VISIBLE else View.GONE
        druid_selection.visibility = if (pos == 3) View.VISIBLE else View.GONE
        fighter_selection.visibility = if (pos == 4) View.VISIBLE else View.GONE
        monk_selection.visibility = if (pos == 5) View.VISIBLE else View.GONE
        paladin_selection.visibility = if (pos == 6) View.VISIBLE else View.GONE
        ranger_selection.visibility = if (pos == 7) View.VISIBLE else View.GONE
        rogue_selection.visibility = if (pos == 8) View.VISIBLE else View.GONE
        sorcerer_selection.visibility = if (pos == 9) View.VISIBLE else View.GONE
        warlock_selection.visibility = if (pos == 10) View.VISIBLE else View.GONE
        wizard_selection.visibility = if (pos == 11) View.VISIBLE else View.GONE
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = CharacterAppearanceFragment()
    }
}
