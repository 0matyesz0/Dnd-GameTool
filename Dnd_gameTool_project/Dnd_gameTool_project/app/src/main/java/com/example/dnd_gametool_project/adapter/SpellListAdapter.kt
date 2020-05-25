package com.example.dnd_gametool_project.adapter

import android.R.attr.level
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dnd_gametool_project.R
import com.example.dnd_gametool_project.ViewSpellActivity
import com.example.dnd_gametool_project.model.Spell
import java.util.*


class SpellListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SpellListAdapter.SpellViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var spells = emptyList<Spell>() // cached copy of characters.
    private var filteredSpells : MutableCollection<Spell> = mutableListOf()
    private val resources = context.resources
    private val activityContext = context

    inner class SpellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val spellItemName: TextView = itemView.findViewById(R.id.spellNameText)
        val spellAvailableClasses: TextView = itemView.findViewById(R.id.spellAvailableClassesText)
        val spellItemImage: ImageView = itemView.findViewById(R.id.cardImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellListAdapter.SpellViewHolder {
        val itemView = inflater.inflate(R.layout.spell_recycler_item, parent, false)
        return SpellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val current = filteredSpells.elementAt(position)
        holder.spellItemName.text = current.name
        holder.spellAvailableClasses.text = "Casters: " + current.classes

        holder.itemView.setOnClickListener {
            val intent = Intent(activityContext, ViewSpellActivity::class.java)
            intent.putExtra("gameSpell", current)
            activityContext.startActivity(intent)
        }

    }

    public fun filter(classFilter: String, levelFilter: String){
        Thread(Runnable {
            filteredSpells.clear()
            if (classFilter == "None" && levelFilter == "None") {
                filteredSpells.addAll(spells)
            } else {
                for (spell in spells) {
                    if (
                        spell.classes.toLowerCase(Locale.ROOT).contains(classFilter.toLowerCase(
                            Locale.ROOT))
                        && spell.level.toLowerCase(Locale.ROOT).contains(levelFilter.toLowerCase(
                            Locale.ROOT))
                    ) {
                        filteredSpells.add(spell)
                    }
                }
            }
            (activityContext as Activity).runOnUiThread {
                // Notify the List that the DataSet has changed...
                notifyDataSetChanged()
            }
        }).start()

    }

    internal fun setSpells(spells: List<Spell>) {
        this.spells = spells
        this.filteredSpells.addAll(this.spells)
        notifyDataSetChanged()
    }

    //override fun getItemCount() = spells.size
    override fun getItemCount() = filteredSpells.size
}