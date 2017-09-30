package hackathon.com.salemovedroid.utils

import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by aareundo on 30/09/2017.
 */

class Utils {
    companion object {
        @Throws(IOException::class)
        @JvmStatic fun readFromAssets(assets: AssetManager, filename: String): String {

            val reader = BufferedReader(InputStreamReader(assets.open(filename)))

            // do reading, usually loop until end of file reading
            val sb = StringBuilder()
            var mLine: String? = reader.readLine()
            while (mLine != null) {
                sb.append(mLine) // process line
                mLine = reader.readLine()
            }
            reader.close()
            return sb.toString()
        }
    }
}