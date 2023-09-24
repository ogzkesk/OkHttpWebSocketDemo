package com.ogzkesk.websocket

import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class IWebSocketListener(val onReceive: (String) -> Unit) : WebSocketListener() {




    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        println("webSocket connected!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        onReceive(text)
        println("received :: $text")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        println("closing.. $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        println("Failed :: ${t.message}")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        println("WebSocket closed ::$webSocket $code $reason")
    }


    companion object {
        private const val apiKey = "BbHgJTmTMoj8JovqIBA14joSqDWtoUTOoPeHBGxE"
        private const val channelId = 1
        const val CLOSING_CODE = 1001
        const val URL = "wss://free.blr2.piesocket.com/v3/$channelId?api_key=$apiKey&notify_self=1"
    }
}