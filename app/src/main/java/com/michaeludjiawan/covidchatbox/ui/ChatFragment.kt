package com.michaeludjiawan.covidchatbox.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaeludjiawan.covidchatbox.R
import com.michaeludjiawan.covidchatbox.data.api.Result
import com.michaeludjiawan.covidchatbox.data.model.Message
import com.michaeludjiawan.covidchatbox.data.model.SenderType
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val viewModel by viewModel<ChatViewModel>()
    private val messageAdapter = MessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.updateData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initErrorBox()
        initChatList()
        initChatBox()
    }

    private fun initChatList() {
        rv_chat_messages.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter
        }

        viewModel.messageResponse.observe(viewLifecycleOwner, { message ->
            messageAdapter.addMessage(Message(message, SenderType.RECEIVED))
            rv_chat_messages.scrollToPosition(rv_chat_messages.adapter!!.itemCount - 1)
        })
    }

    private fun initChatBox() {
        btn_chatbox_send.setOnClickListener {
            val message = et_chatbox_input.text.toString()
            messageAdapter.addMessage(Message(message, SenderType.SENT))
            clearInputBox()

            viewModel.processMessage(message)
        }
    }

    private fun clearInputBox() {
        et_chatbox_input.text = null
    }

    private fun initErrorBox() {
        viewModel.updateDataLoading.observe(viewLifecycleOwner, { isLoading ->
            pb_chat_error_loader.isVisible = isLoading
            btn_chat_error_retry.isVisible = !isLoading
        })

        viewModel.updateDataResult.observe(viewLifecycleOwner, { result ->
            layout_chatbox.isVisible = result is Result.Success
            layout_error_box.isVisible = result is Result.Error
        })

        btn_chat_error_retry.setOnClickListener {
            viewModel.updateData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_chat, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_help -> {
                showHelpDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.command_list_title))
            .setMessage(getString(R.string.command_list_message))
            .setPositiveButton(getString(R.string.command_list_btn_label), null)
            .show()
    }
}