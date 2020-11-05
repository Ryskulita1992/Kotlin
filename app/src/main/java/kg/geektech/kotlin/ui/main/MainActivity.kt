package kg.geektech.kotlin.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kg.geektech.kotlin.ui.second.SecondActivity
import kg.geektech.kotlin.databinding.ActivityMainBinding
import kg.geektech.kotlin.ui.extension.toastText
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

const val KEY_TEXT = "text"
const val MAIN_ACTIVITY = 0

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        openActivity();
    }

    private fun openActivity() {
        binding.buttonMain.setOnClickListener {
            val dataMain = binding.editTextMain.text.trim().toString()
            if (dataMain.isNotEmpty()) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(KEY_TEXT, editTextMain.text.trim().toString())
                startActivityForResult(intent, 0)
            } else {
                toastText(this, "You should input value")


            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> {
                if (resultCode == Activity.RESULT_OK)
                    editTextMain.setText(data?.getStringExtra("result"))
                list.apply {
                    add(data?.getStringExtra("result").toString())
                }
                showHistory()
            }

        }
    }

    private fun showHistory() {
        Log.i("TAG", "showHistory: ")
        binding.showButton.setOnClickListener {
            binding.history.setText(list.toString())
            Log.i("TAG", "onActivityResult: $list")

        }
    }


}