package com.example.dnd_gametool_project

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dnd_gametool_project.adapter.SpellListAdapter
import com.example.dnd_gametool_project.viewmodel.SpellViewModel


class SpellActivity : AppCompatActivity() {

    private lateinit var spellViewModel: SpellViewModel
    private lateinit var spellAdapter: SpellListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell)

        spellViewModel = ViewModelProvider(this).get(SpellViewModel::class.java)

        // level spinner:
        val levels: MutableList<String> = mutableListOf()
        levels.add("cantrip")
        for (x in 0..10) {
            levels.add((x + 1).toString())
        }

        val levelAdapter =
            ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, levels)
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val levelSpinner = findViewById<Spinner>(R.id.level_spinner)
        levelSpinner.adapter = levelAdapter
        levelSpinner.prompt = resources.getString(R.string.levelSpinner)

        // class spinner:
        var classes = arrayOf(
            resources.getString(R.string.barbarian),
            resources.getString(R.string.bard),
            resources.getString(R.string.cleric),
            resources.getString(R.string.druid),
            resources.getString(R.string.fighter),
            resources.getString(R.string.monk),
            resources.getString(R.string.paladin),
            resources.getString(R.string.ranger),
            resources.getString(R.string.rogue),
            resources.getString(R.string.sorcerer),
            resources.getString(R.string.warlock),
            resources.getString(R.string.wizard)
        )
        val classAdapter =
            ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, classes)
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val classSpinner = findViewById<Spinner>(R.id.class_spinner)
        classSpinner.adapter = classAdapter
        classSpinner.prompt = resources.getString(R.string.classSpinner)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        spellAdapter = SpellListAdapter(this)
        recyclerView.adapter = spellAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        spellViewModel.spells.observe(this, Observer { spells ->
            // Update the cached copy of the spells in the adapter.
            spells?.let { spellAdapter.setSpells(it) }
        })

        levelSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                onSpinnerValueChange(
                    classSpinner.getSelectedItem().toString(),
                    levelSpinner.getSelectedItem().toString()
                )
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        classSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                onSpinnerValueChange(
                    classSpinner.getSelectedItem().toString(),
                    levelSpinner.getSelectedItem().toString()
                )
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    fun onSpinnerValueChange(
        classFilter: String,
        levelFilter: String
    ): Boolean {
        spellAdapter.filter(classFilter, levelFilter)
        return true
    }
}
