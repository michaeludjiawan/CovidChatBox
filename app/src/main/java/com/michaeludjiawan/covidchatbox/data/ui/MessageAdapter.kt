package com.michaeludjiawan.covidchatbox.data.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaeludjiawan.covidchatbox.data.model.Message
import com.michaeludjiawan.covidchatbox.data.model.SenderType

const val VIEW_TYPE_MESSAGE_SENT = 1
const val VIEW_TYPE_MESSAGE_RECEIVED = 2

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages = ArrayList<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MESSAGE_SENT -> SentMessageViewHolder.create(parent)
            VIEW_TYPE_MESSAGE_RECEIVED -> ReceivedMessageViewHolder.create(parent)
            else -> throw NotImplementedError()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]

        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageViewHolder).bind(message)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageViewHolder).bind(message)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]

        return if (message.senderType == SenderType.SENT) VIEW_TYPE_MESSAGE_SENT
        else VIEW_TYPE_MESSAGE_RECEIVED
    }

    override fun getItemCount(): Int = messages.size

    fun addMessage(message: Message) {
        messages.add(message)
        notifyItemInserted(messages.lastIndex)
    }
}