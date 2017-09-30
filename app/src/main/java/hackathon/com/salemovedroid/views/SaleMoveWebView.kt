package hackathon.com.salemovedroid.views

import android.content.Context
import android.webkit.WebView
import android.content.pm.ApplicationInfo

/**
 * Created by aareundo on 30/09/2017.
 */
class SaleMoveWebView(context: Context) : WebView(context) {

    init {

        clearCache(true)

        settings.javaScriptEnabled = true

        load()

        //if your build is in debug mode, enable inspecting of webviews
        if (0 != context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }

    fun load() {

        val url = "http://rocktoriin.ee/salemove/"
        loadUrl(url)
    }
}