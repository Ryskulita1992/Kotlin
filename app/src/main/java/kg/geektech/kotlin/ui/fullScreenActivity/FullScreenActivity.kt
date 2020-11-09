package kg.geektech.kotlin.ui.fullScreenActivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kg.geektech.kotlin.databinding.ActivityFullScreenBinding
import kg.geektech.kotlin.ui.extensions.loadImage
import kg.geektech.kotlin.ui.second.KEY_URL

class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater);
        setContentView(binding.root)
        getIntentUrl()
    }

    private fun getIntentUrl() {
       var data = intent.getStringExtra(KEY_URL)
        binding.fullScreen.loadImage(data.toString())
        Log.e("TAG", "getIntentUrl: "+ data.toString())
        //binding.fullScreen.setImageURI(data)
    }}