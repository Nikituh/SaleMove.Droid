package hackathon.com.salemovedroid.views

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import hackathon.com.salemovedroid.R
import hackathon.com.salemovedroid.model.Operator
import hackathon.com.salemovedroid.views.base.BaseView
import org.jetbrains.anko.doAsync
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import com.squareup.picasso.Picasso
import hackathon.com.salemovedroid.views.base.setFrame
import java.io.IOException
import java.net.HttpURLConnection


/**
 * Created by aareundo on 30/09/2017.
 */

class OperatorCell(context: Context) : BaseView(context) {

    var trueY: Int = -1

    val avatar = ImageView(context)
    val nameLabel = TextView(context)
    val name = TextView(context)
    val chat = ImageView(context)
    val call = ImageView(context)

    var operator: Operator? = null

    init {

        setBackgroundColor(Color.WHITE)

        avatar.scaleType = ImageView.ScaleType.CENTER_CROP
        avatar.adjustViewBounds = true
        addView(avatar)

        nameLabel.gravity = Gravity.BOTTOM
        nameLabel.text = "Name"
        nameLabel.textSize = 11f
        addView(nameLabel)

        name.gravity = Gravity.TOP
        name.typeface = Typeface.DEFAULT_BOLD
        name.textSize = 13.0f
        addView(name)

        chat.setImageResource(R.drawable.ic_chat_icon)
        chat.scaleType = ImageView.ScaleType.CENTER_INSIDE
        chat.elevation = 10.0f
        chat.isClickable = true
        chat.isFocusable = true
        addView(chat)

        call.setImageResource(R.drawable.ic_operator_status_on_call)
        call.scaleType = ImageView.ScaleType.CENTER_INSIDE
        call.elevation = 10.0f
        call.isClickable = true
        call.isFocusable = true
        addView(call)

        elevation = 5.0f
    }

    var avatarSize: Int = -1

    override fun layoutSubviews() {

        val padding = (10 * getDensity()).toInt()
        avatarSize = frame.height - 2 * padding
        val buttonSize = frame.height - 2 * (frame.height / 4)

        var x = padding
        var y = padding
        var w = avatarSize
        var h = w

        avatar.setFrame(x, y, w, h)

        x += w + padding
        w = (frame.width / 2.5).toInt()

        h = avatarSize / 2
        y = padding

        nameLabel.setFrame(x, y, w, h)

        y += h

        name.setFrame(x, y, w, h)

        x = frame.width - (2.5 * buttonSize + 2 * padding).toInt()
        y = padding
        w = buttonSize
        h = avatarSize

        chat.setFrame(x, y, w, h)

        x += (1.5 * w).toInt()

        call.setFrame(x, y, w, h)
    }

    fun update(operator: Operator) {

        this.operator = operator

        if (!operator.imageUrl.isEmpty()) {
            Picasso
                    .with(context)
                    .load(operator.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(avatar)
        } else {
            (context as Activity).runOnUiThread {
                avatar.setImageResource(R.drawable.default_avatar_250x250)
            }
        }

        name.text = operator.name
    }

    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = java.net.URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val original = BitmapFactory.decodeStream(connection.inputStream)
            resize(original, avatarSize, avatarSize)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun resize(image: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        var image = image
        if (maxHeight > 0 && maxWidth > 0) {
            val width = image.width
            val height = image.height
            val ratioBitmap = width.toFloat() / height.toFloat()
            val ratioMax = maxWidth.toFloat() / maxHeight.toFloat()

            var finalWidth = maxWidth
            var finalHeight = maxHeight
            if (ratioMax > 1) {
                finalWidth = (maxHeight.toFloat() * ratioBitmap).toInt()
            } else {
                finalHeight = (maxWidth.toFloat() / ratioBitmap).toInt()
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true)
            return image
        } else {
            return image
        }
    }
}