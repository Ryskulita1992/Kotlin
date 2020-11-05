package kg.geektech.kotlin.ui.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun toastText(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}




