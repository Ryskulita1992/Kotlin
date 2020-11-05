package kg.geektech.kotlin.ui.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kg.geektech.kotlin.databinding.ActivitySecondBinding
import kg.geektech.kotlin.ui.main.KEY_TEXT
import kg.geektech.kotlin.ui.extension.toastText

class
SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var data: String? = ""
    private var newData: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater);
        setContentView(binding.root)
        getIntentRes();
        returnResultAction()
    }

    private fun getIntentRes() {
        data = intent.getStringExtra(KEY_TEXT)
        binding.editTextSecond.setText(data).toString()


    }

    private fun returnResultAction() {
       binding.buttonSecond.setOnClickListener {
             newData = binding.editTextSecond.text.toString()
            if (newData == data)
                toastText(this, "Change the data")
            else returnData(newData.toString())

        }
    }

    private fun returnData(newData: String) {
        if (newData.isNotEmpty()) {
            val intent = Intent()
            intent.putExtra("result", newData)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}