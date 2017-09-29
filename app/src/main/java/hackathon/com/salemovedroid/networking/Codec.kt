package hackathon.com.salemovedroid.networking

import hackathon.com.salemovedroid.model.Operator
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by aareundo on 29/09/2017.
 */
class Codec {
    companion object {
        fun parseOperators(json: JSONArray): MutableList<Operator> {

            val result = mutableListOf<Operator>()

            for (i in 0..(json.length() - 1)) {
                val child = json.getJSONObject(i)
                result.add(parseOperator(child))
            }

            return result
        }

        private fun parseOperator(json: JSONObject): Operator {
            val result = Operator()

            result.name = json["name"] as String
            result.email = json["email"] as String
            result.phone = json["phone"] as String
            result.available = json["available"] as Boolean

            try {
                val picture = json["picture"] as JSONObject
                result.imageUrl = picture["url"] as String  
            } catch (e: Exception) {

            }

            return result
        }
    }
}