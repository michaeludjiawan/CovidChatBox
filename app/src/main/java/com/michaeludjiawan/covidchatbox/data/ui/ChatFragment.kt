package com.michaeludjiawan.covidchatbox.data.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaeludjiawan.covidchatbox.R
import com.michaeludjiawan.covidchatbox.data.model.Message
import com.michaeludjiawan.covidchatbox.data.model.SenderType
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val viewModel by viewModel<ChatViewModel>()
    private val messageAdapter = MessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.updateData()
    }

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

            viewModel.processMessage(message)
        }

        viewModel.messageResponse.observe(viewLifecycleOwner, { message ->
            messageAdapter.addMessage(Message(message, SenderType.RECEIVED))
        })
    }

    private fun clearInputBox() {
        et_chatbox_input.text = null
    }
}