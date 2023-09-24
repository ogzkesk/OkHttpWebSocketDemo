package com.ogzkesk.websocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ogzkesk.websocket.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val socketListener = IWebSocketListener { msg ->
        runOnUiThread {
            binding.tvOutput.text = msg
        }
    }

    private val client = OkHttpClient.Builder().build()
    private val request = Request.Builder().url(IWebSocketListener.URL).build()
    private var socket: WebSocket = client.newWebSocket(request, socketListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSend.setOnClickListener { send() }
            btnClose.setOnClickListener { close() }
        }
    }

    private fun close() {
        try {
            socket.close(IWebSocketListener.CLOSING_CODE, "closeButtonClicked")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun send() {
        try {
            val textMessage = binding.etSent.text.toString()
            socket.send(textMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.close(IWebSocketListener.CLOSING_CODE, "onDestroyed")
    }
}