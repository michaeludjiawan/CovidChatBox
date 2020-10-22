package com.michaeludjiawan.covidchatbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.michaeludjiawan.covidchatbox.data.ui.ChatFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main_container_host, ChatFragment())
            .commit()
    }
}