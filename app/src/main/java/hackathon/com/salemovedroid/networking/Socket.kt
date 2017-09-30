package hackathon.com.salemovedroid.networking

import com.github.nkzawa.socketio.client.IO

/**
 * WebSocket implementation is too much of a hassle for a 24-hour hackathon,
 * opted for a WebView based solution.
 * But theoretically a connection could be established with this socket.IO library
 */
class Socket {

//    val protocol = "wss"
    val protocol = "https"
    val url = "$protocol://kluster.beta.salemove.com/socket.io"

    val socket = IO.socket(url)

    fun connect() {
        socket.connect()
    }

    fun isConnected(): Boolean {
        return socket.connected()
    }
}