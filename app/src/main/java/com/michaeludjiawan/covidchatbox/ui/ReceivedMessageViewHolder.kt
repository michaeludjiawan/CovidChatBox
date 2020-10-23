package com.michaeludjiawan.covidchatbox.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaeludjiawan.covidchatbox.R
import com.michaeludjiawan.covidchatbox.data.model.Message
import kotlinx.android.synthetic.main.item_message_received.view.*

class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(message: Message) = with(itemView) {
        text_message_received_body.text = message.message
    }

    companion object {
        fun create(parent: ViewGroup): ReceivedMessageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_received, parent, false)
            return ReceivedMessageViewHolder(view)
        }
    }
}