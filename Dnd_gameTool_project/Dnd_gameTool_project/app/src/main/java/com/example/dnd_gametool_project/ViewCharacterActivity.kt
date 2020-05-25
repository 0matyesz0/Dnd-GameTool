package com.example.dnd_gametool_project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dnd_gametool_project.model.GameCharacter
import com.example.dnd_gametool_project.util.CharacterValueConverter
import com.example.dnd_gametool_project.viewmodel.GameCharacterViewModel
import kotlinx.android.synthetic.main.activity_view_character.*


class ViewCharacterActivity : AppCompatActivity() {
    private lateinit var gameCharacterViewModel: GameCharacterViewModel
    private var currentCharacter: GameCharacter? = null
    private var classNumber = 0
    private var raceNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_character)

        gameCharacterViewModel = ViewModelProvider(this).get(GameCharacterViewModel::class.java)

        currentCharacter = intent?.getParcelableExtra<GameCharacter>("gameCharacter")

        // setting the edit texts
        name_value.setText(currentCharacter?.name.toString())
        intelligence_value.setText(currentCharacter?.intelligence.toString())
        constitution_value.setText(currentCharacter?.constitution.toString())
        armourClass_value.setText(currentCharacter?.armourClass.toString())
        charisma_value.setText(currentCharacter?.charisma.toString())
        level_value.setText(currentCharacter?.level.toString())
        dexterity_value.setText(currentCharacter?.dexterity.toString())
        experience_value.setText(currentCharacter?.experience.toString())
        gold_value.setText(currentCharacter?.gold.toString())
        hitDice_value.setText(currentCharacter?.hitDice.toString())
        hp_value.setText(currentCharacter?.healthPoints.toString())
        initiative_value.setText(currentCharacter?.initiative.toString())
        perception_value.setText(currentCharacter?.perception.toString())
        proficiency_value.setText(currentCharacter?.proficiency.toString())
        speed_value.setText(currentCharacter?.speed.toString())
        strength_value.setText(currentCharacter?.strength.toString())
        wisdom_value.setText(currentCharacter?.wisdom.toString())

        // Converting race and class values
        raceNumber = currentCharacter?.race!!
        val raceString = CharacterValueConverter.convertToRace(raceNumber, this)
        race_value.text = raceString

        classNumber = currentCharacter?.character_class!!
        val classString = CharacterValueConverter.convertToClass(classNumber, this)
        class_value.text = classString

        val classImg = CharacterValueConverter.convertToClassImage(classNumber)
        class_img.setImageResource(classImg)

        // setting the ViewModel values.

        gameCharacterViewModel.armourClass.setValue(armourClass_value.text.toString().toInt())
        gameCharacterViewModel.character_class.setValue(classNumber)
        gameCharacterViewModel.charisma.setValue(charisma_value.text.toString().toInt())
        gameCharacterViewModel.constitution.setValue(constitution_value.text.toString().toInt())
        gameCharacterViewModel.dexterity.setValue(dexterity_value.text.toString().toInt())
        gameCharacterViewModel.experience.setValue(experience_value.text.toString().toInt())
        gameCharacterViewModel.gold.setValue(gold_value.text.toString().toInt())
        gameCharacterViewModel.hitDice.setValue(hitDice_value.text.toString().toInt())
        gameCharacterViewModel.healthPoints.setValue(hp_value.text.toString().toInt())
        gameCharacterViewModel.initiative.setValue(initiative_value.text.toString().toInt())
        gameCharacterViewModel.intelligence.setValue(intelligence_value.text.toString().toInt())
        gameCharacterViewModel.level.setValue(level_value.text.toString().toInt())
        gameCharacterViewModel.name.setValue(name_value.text.toString())
        gameCharacterViewModel.perception.setValue(perception_value.text.toString().toInt())
        gameCharacterViewModel.proficiency.setValue(proficiency_value.text.toString().toInt())
        gameCharacterViewModel.race.setValue(raceNumber)
        gameCharacterViewModel.speed.setValue(speed_value.text.toString().toInt())
        gameCharacterViewModel.strength.setValue(strength_value.text.toString().toInt())
        gameCharacterViewModel.wisdom.setValue(wisdom_value.text.toString().toInt())

        // Progress Bar:
        var currentLevel = level_value.text.toString().toInt()
        var currentExperience = experience_value.text.toString().toInt()
        // text of progress bar:
        expProgressText.text =
            currentExperience.toString() + "/" + (CharacterValueConverter.getExperienceLeftToLevelUp(
                currentLevel,
                currentExperience
            ) + currentExperience).toString()
        // progress Bar values:
        expProgressBar.max = CharacterValueConverter.getExperienceLeftToLevelUp(
            currentLevel,
            currentExperience
        ) + currentExperience
        expProgressBar.progress = currentExperience

        // experience onClickListener
        var newLevel = 0
        var newExperience = 0

        experience_value.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    aft: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {

                    //call your function here of calculation here
                    updateEditTexts()
                }
            }

        )

        level_value.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    aft: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {

                    //call your function here of calculation here
                    updateProgressBar()
                }
            }

        )
    }

    private fun updateEditTexts() {
        try {
            val newLevel = CharacterValueConverter.getLevelFromGivenEcperience(
                experience_value.text.toString().toInt()
            )
            val newExperience = CharacterValueConverter.getExperienceLeftToLevelUp(
                newLevel,
                experience_value.text.toString().toInt()
            )
            level_value.setText(newLevel.toString())
            expProgressText.text =
                experience_value.text.toString() + "/" + (CharacterValueConverter.getExperienceLeftToLevelUp(
                    newLevel,
                    newExperience
                ) + newExperience).toString()
            expProgressBar.max = CharacterValueConverter.getExperienceLeftToLevelUp(
                newLevel,
                newExperience
            ) + newExperience
            expProgressBar.progress = newExperience
        } catch (e: NumberFormatException) {

        }
    }

    private fun updateProgressBar() {
        try {
            expProgressText.text =
                experience_value.text.toString() + "/" + (CharacterValueConverter.getExperienceLeftToLevelUp(
                    level_value.text.toString().toInt(),
                    experience_value.text.toString().toInt()
                ) + experience_value.text.toString().toInt()).toString()

            expProgressBar.max = CharacterValueConverter.getExperienceLeftToLevelUp(
                level_value.text.toString().toInt(),
                experience_value.text.toString().toInt()
            ) + experience_value.text.toString().toInt()
        } catch (e: NumberFormatException) {

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        gameCharacterViewModel = ViewModelProvider(this).get(GameCharacterViewModel::class.java)

        // check if any of the values has been modified.

        if (
            gameCharacterViewModel.armourClass.value.toString() == armourClass_value.text.toString() &&
            gameCharacterViewModel.charisma.value.toString() == charisma_value.text.toString() &&
            gameCharacterViewModel.constitution.value.toString() == constitution_value.text.toString() &&
            gameCharacterViewModel.dexterity.value.toString() == dexterity_value.text.toString() &&
            gameCharacterViewModel.experience.value.toString() == experience_value.text.toString() &&
            gameCharacterViewModel.gold.value.toString() == gold_value.text.toString() &&
            gameCharacterViewModel.hitDice.value.toString() == hitDice_value.text.toString() &&
            gameCharacterViewModel.healthPoints.value.toString() == hp_value.text.toString() &&
            gameCharacterViewModel.initiative.value.toString() == initiative_value.text.toString() &&
            gameCharacterViewModel.intelligence.value.toString() == intelligence_value.text.toString() &&
            gameCharacterViewModel.level.value.toString() == level_value.text.toString() &&
            gameCharacterViewModel.name.value == name_value.text.toString() &&
            gameCharacterViewModel.perception.value.toString() == perception_value.text.toString() &&
            gameCharacterViewModel.proficiency.value.toString() == proficiency_value.text.toString() &&
            gameCharacterViewModel.speed.value.toString() == speed_value.text.toString() &&
            gameCharacterViewModel.strength.value.toString() == strength_value.text.toString() &&
            gameCharacterViewModel.wisdom.value.toString() == wisdom_value.text.toString()
        ) {
            return
        } else {
            try {
                // updating database with edittext values.

                val characterUpdate = GameCharacter(
                    currentCharacter!!.characterId,  // <-- null reference exception
                    armourClass_value.text.toString().toInt(),
                    classNumber,
                    charisma_value.text.toString().toInt(),
                    constitution_value.text.toString().toInt(),
                    dexterity_value.text.toString().toInt(),
                    experience_value.text.toString().toInt(),
                    gold_value.text.toString().toInt(),
                    hitDice_value.text.toString().toInt(),
                    hp_value.text.toString().toInt(),
                    initiative_value.text.toString().toInt(),
                    intelligence_value.text.toString().toInt(),
                    level_value.text.toString().toInt(),
                    name_value.text.toString(),
                    perception_value.text.toString().toInt(),
                    proficiency_value.text.toString().toInt(),
                    raceNumber,
                    speed_value.text.toString().toInt(),
                    strength_value.text.toString().toInt(),
                    wisdom_value.text.toString().toInt()
                )

                gameCharacterViewModel.update(characterUpdate)

                Toast.makeText(
                    applicationContext,
                    "Saved changes!",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    applicationContext,
                    "Something was wrong with the given values!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
