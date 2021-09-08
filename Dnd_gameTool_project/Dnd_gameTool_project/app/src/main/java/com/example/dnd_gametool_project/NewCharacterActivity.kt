package com.example.dnd_gametool_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.example.dnd_gametool_project.fragment.CharacterAppearanceFragment
import com.example.dnd_gametool_project.fragment.CharacterDataFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewCharacterActivity : AppCompatActivity() {

    private lateinit var editCharacterView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        var fragment = CharacterDataFragment()
        // Naming convention is bad
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }
}
