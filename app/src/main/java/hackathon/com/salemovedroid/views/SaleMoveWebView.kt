package hackathon.com.salemovedroid.views

import android.content.Context
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by aareundo on 30/09/2017.
 */
class SaleMoveWebView(context: Context) : WebView(context) {

    init {
        settings.javaScriptEnabled = true
        settings.pluginState = WebSettings.PluginState.ON

        val url = "http://rocktoriin.ee/salemove/"
        loadUrl(url)
    }
}