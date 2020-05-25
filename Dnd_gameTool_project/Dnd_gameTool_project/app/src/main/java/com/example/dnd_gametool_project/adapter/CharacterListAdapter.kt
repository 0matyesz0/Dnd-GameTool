package com.example.dnd_gametool_project.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dnd_gametool_project.MainActivity
import com.example.dnd_gametool_project.R
import com.example.dnd_gametool_project.ViewCharacterActivity
import com.example.dnd_gametool_project.model.GameCharacter
import com.example.dnd_gametool_project.util.CharacterValueConverter
import kotlinx.android.synthetic.main.character_recycler_item.view.*

class CharacterListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var characters = emptyList<GameCharacter>() // cached copy of characters.
    private val resources = context.resources
    private val activityContext = context

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterItemName: TextView = itemView.findViewById(R.id.textView)
        val characterItemImage: ImageView = itemView.findViewById(R.id.cardImg)
        val characterItemRace: TextView = itemView.findViewById(R.id.raceTag)
        val characterItemClass: TextView = itemView.findViewById(R.id.classTag)
        val characterItemLevel: TextView = itemView.findViewById(R.id.levelTag)
        val characterItemGold: TextView = itemView.findViewById(R.id.goldTag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = inflater.inflate(R.layout.character_recycler_item, parent, false)

        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = characters[position]
        holder.characterItemName.text = current.name
        holder.characterItemClass.text = CharacterValueConverter.convertToClass(current.character_class, activityContext)
        holder.characterItemGold.text = "gold: " + current.gold.toString()
        holder.characterItemLevel.text = "level " + current.level.toString()
        holder.characterItemRace.text = CharacterValueConverter.convertToRace(current.race, activityContext)
        holder.characterItemImage.setImageResource(CharacterValueConverter.convertToClassImage(current.character_class))

        holder.itemView.setOnClickListener {
            val intent = Intent(activityContext, ViewCharacterActivity::class.java)
            intent.putExtra("gameCharacter", current)

            activityContext.startActivity(intent)
        }
        holder.itemView.delButton.setOnClickListener{
            (activityContext as MainActivity).gameCharacterViewModel.delete(current.characterId)
        }
    }

    internal fun setCharacters(characters: List<GameCharacter>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size
}