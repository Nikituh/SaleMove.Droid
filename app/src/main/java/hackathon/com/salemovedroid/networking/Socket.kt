package hackathon.com.salemovedroid.networking

import com.github.nkzawa.engineio.client.Socket
import com.github.nkzawa.socketio.client.IO
import org.jetbrains.annotations.NotNull

/**
 * Created by aareundo on 30/09/2017.
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