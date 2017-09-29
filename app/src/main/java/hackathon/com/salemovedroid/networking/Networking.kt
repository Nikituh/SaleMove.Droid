package hackathon.com.salemovedroid.networking

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import hackathon.com.salemovedroid.model.Operator
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import java.net.URL

/**
 * Created by aareundo on 29/09/2017.
 */
class Networking {

    /**
     * WebRCT: https://webrtc.org/native-code/android/
     * Link to documentaion: https://github.com/kittinunf/Fuel
     */

    companion object {
        @JvmStatic var token = ""
    }

    private val site_id = "4acb779c-a1d8-4d38-a4e6-402b7ed1453d"
    private val base_url = "https://api.beta.salemove.com/"
    private val operator_url = base_url + "operators?site_ids[]=$site_id&include_offline=false"

    fun getOperators(callback: (list: MutableList<Operator>) -> Unit) {

        FuelManager.instance.baseHeaders = getHeaders()

        var request = Request()
        request.url = URL(operator_url)
        request.httpMethod = Method.GET

        Fuel.request(request).responseJson { _, _, result ->
            result.fold(success = { json ->

                var operator_json = json.obj()["operators"] as JSONArray
                val operators = Codec.parseOperators(operator_json)
                callback(operators)

            }, failure = { error ->
                Log.e("qdp error", error.toString())

            })

        }
    }

    private fun getHeaders() : MutableMap<String, String> {

        return mutableMapOf(
                "Authorization" to "Token $token",
                "Accept" to "application/vnd.salemove.v1+json"
        )

    }
}