package com.incorp.ichat

// DialogsAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DialogsAdapter(private val context: Context, private val chats: List<Chat>) : BaseAdapter() {
    override fun getCount(): Int = chats.size

    override fun getItem(position: Int): Chat = chats[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val chat = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_dialog, parent, false)

        val userNameTextView: TextView = view.findViewById(R.id.userNameTextView)
        val lastMessageTextView: TextView = view.findViewById(R.id.lastMessageTextView)
        val newMessagesCountTextView: TextView = view.findViewById(R.id.newMessagesCountTextView)
        val userPhotoImageView: ImageView = view.findViewById(R.id.userPhotoImageView)

        userNameTextView.text = chat.withUserFullname
        lastMessageTextView.text = chat.lastMessage
        newMessagesCountTextView.text = chat.newMessagesCount

        // Загрузка изображения с помощью Glide
        Glide.with(context)
            .load(chat.withUserPhotoUrl)
            .into(userPhotoImageView)

        return view

    }


}
