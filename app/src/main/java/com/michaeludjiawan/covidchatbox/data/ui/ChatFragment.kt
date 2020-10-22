package com.michaeludjiawan.covidchatbox.data.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaeludjiawan.covidchatbox.R
import com.michaeludjiawan.covidchatbox.data.model.Message
import com.michaeludjiawan.covidchatbox.data.model.SenderType
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val messageAdapter = MessageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_chat_messages.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter
        }

        btn_chatbox_send.setOnClickListener {
            val message = et_chatbox_input.text.toString()
            messageAdapter.addMessage(Message(message, SenderType.SENT))
            clearInputBox()
        }
    }

    private fun clearInputBox() {
        et_chatbox_input.text = null
    }
}