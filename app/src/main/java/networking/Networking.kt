package hackathon.com.salemovedroid.networking

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

/**
 * Created by aareundo on 29/09/2017.
 */
class Networking {

    /**
     * WebRCT: https://webrtc.org/native-code/android/
     */

    companion object {
        @JvmStatic var token = ""
    }

    private val site_id = "4acb779c-a1d8-4d38-a4e6-402b7ed1453d"
    private val base_url = "https://api.beta.salemove.com/"
    private val operator_url = base_url + "operators?site_ids[]=$site_id&include_offline=false"

    fun doStuff() {

        val headers = mapOf(
                "Authorization" to "Token $token",
                "Accept" to "application/vnd.salemove.v1+json"
        )

        FuelManager.instance.baseHeaders = headers

        Fuel.get(operator_url).response { request, response, result ->
            println(request)
            println(response)

            result.fold(success = { json ->
                val string = json.sortedArray().toString()

                Log.d("qdp success", string)
            }, failure = { error ->
                Log.e("qdp error", error.toString())
            })
        }

    }
}