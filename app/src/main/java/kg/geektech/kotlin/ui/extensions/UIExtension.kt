package kg.geektech.kotlin.ui.extensions

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import kg.geektech.kotlin.R
import kg.geektech.kotlin.ui.second.rv.Adapter

fun toastText(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun ImageView.loadImage(url: String) {
    Glide
        .with(context)
        .load(url)
        .into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun Context.alert(
    @StringRes titleRes: Int? = null, @StringRes message: Int, @StringRes positiveBtn: Int, @StringRes negativeBtn: Int,
    action: () -> Unit
) {
    MaterialDialog(this).show {
        titleRes?.let { title(it) }
        message(res = message)
        positiveButton(positiveBtn, click = object : DialogCallback {
            override fun invoke(p1: MaterialDialog) {
                action()
            }
        })
        negativeButton(negativeBtn)
    }
}









