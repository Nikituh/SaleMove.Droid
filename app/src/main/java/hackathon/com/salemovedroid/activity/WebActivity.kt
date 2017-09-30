package hackathon.com.salemovedroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import hackathon.com.salemovedroid.views.SaleMoveWebView

class WebActivity : AppCompatActivity() {

    var contentView: SaleMoveWebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView = SaleMoveWebView(this)
        setContentView(contentView)
    }
}
