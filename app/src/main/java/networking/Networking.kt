package hackathon.com.salemovedroid.networking

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import org.jetbrains.anko.doAsync
import java.net.URL

/**
 * Created by aareundo on 29/09/2017.
 */
class Networking {

    /**
     * WebRCT: https://webrtc.org/native-code/android/
     */

    val url = "http://httpbin.org/get"

    fun doStuff() {

        Fuel.get("http://httpbin.org/get").response { request, response, result ->
            println(request)
            println(response)
            val (bytes, error) = result
            if (bytes != null) {
                println(bytes)
            }
        }

    }
}