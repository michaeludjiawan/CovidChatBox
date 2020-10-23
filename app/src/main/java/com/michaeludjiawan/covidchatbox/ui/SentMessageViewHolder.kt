package com.michaeludjiawan.covidchatbox.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaeludjiawan.covidchatbox.R
import com.michaeludjiawan.covidchatbox.data.model.Message
import kotlinx.android.synthetic.main.item_message_sent.view.*

class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(message: Message) = with(itemView) {
        text_message_sent_body.text = message.message
    }

    companion object {
        fun create(parent: ViewGroup): SentMessageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_sent, parent, false)
            return SentMessageViewHolder(view)
        }
    }
}