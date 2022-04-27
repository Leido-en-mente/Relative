package com.cdp.relativeex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cdp.relativeex.R
import com.cdp.relativeex.models.Chat
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.item_chat.view.*

class ChatAd (val chatClick: (Chat) -> Unit): RecyclerView.Adapter<ChatAd.ChatViewHolder>() {

    private lateinit var chatNameText: TextView
    private lateinit var usersTextView: TextView


    var chats: List<Chat> = emptyList()

    fun setData(list: List<Chat>){
        chats = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_chat,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {


        holder.itemView.chatNameText.text = chats[position].name
        holder.itemView.usersTextView.text = chats[position].users.toString()

        holder.itemView.setOnClickListener {
            chatClick(chats[position])
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}