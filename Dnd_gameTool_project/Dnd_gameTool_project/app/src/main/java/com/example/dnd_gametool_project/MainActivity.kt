package com.example.dnd_gametool_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dnd_gametool_project.adapter.CharacterListAdapter
import com.example.dnd_gametool_project.model.GameCharacter
import com.example.dnd_gametool_project.viewmodel.GameCharacterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public lateinit var gameCharacterViewModel: GameCharacterViewModel
    private val newGameCharacterRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CharacterListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        gameCharacterViewModel = ViewModelProvider(this).get(GameCharacterViewModel::class.java)

        gameCharacterViewModel.characters.observe(this, Observer { characters ->
            // Update the cached copy of the characters in the adapter.
            characters?.let { adapter.setCharacters(it) }
        })

        fab.setOnClickListener {
            var intent = Intent(this, NewCharacterActivity::class.java)
            startActivityForResult(intent, newGameCharacterRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (resultCode == Activity.RESULT_OK) {
            val name = intentData?.getStringExtra("character_name")
            val armourClass = intentData?.getStringExtra("armour_class")
            val characterClass = intentData?.getStringExtra("character_class")
            val charisma = intentData?.getStringExtra("charisma")
            val constitution = intentData?.getStringExtra("constitution")
            val dexterity = intentData?.getStringExtra("dexterity")
            val experience = intentData?.getStringExtra("experience")
            val gold = intentData?.getStringExtra("gold")
            val hitDice = intentData?.getStringExtra("hit_dice")
            val hp = intentData?.getStringExtra("hp")
            val initiative = intentData?.getStringExtra("initiative")
            val intelligence = intentData?.getStringExtra("intelligence")
            val level = intentData?.getStringExtra("level")
            val perception = intentData?.getStringExtra("perception")
            val proficiency = intentData?.getStringExtra("proficiency")
            val race = intentData?.getStringExtra("race")
            val speed = intentData?.getStringExtra("speed")
            val strength = intentData?.getStringExtra("strength")
            val wisdom = intentData?.getStringExtra("wisdom")

            val character = GameCharacter(
                0,
                armourClass!!.toInt(),
                characterClass!!.toInt(),
                charisma!!.toInt(),
                constitution!!.toInt(),
                dexterity!!.toInt(),
                experience!!.toInt(),
                gold!!.toInt(),
                hitDice!!.toInt(),
                hp!!.toInt(),
                initiative!!.toInt(),
                intelligence!!.toInt(),
                level!!.toInt(),
                name!!.toString(),
                perception!!.toInt(),
                proficiency!!.toInt(),
                race!!.toInt(),
                speed!!.toInt(),
                strength!!.toInt(),
                wisdom!!.toInt()
            )
            gameCharacterViewModel.insert(character)
        } else {
            Toast.makeText(
                applicationContext,
                "Something went wrong!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_clear -> {
                gameCharacterViewModel.clear()
                return true
            }
            R.id.action_map ->{
                val intent = Intent(this, ShowMapActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_spells ->{
                val intent = Intent(this, SpellActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
