package kg.geektech.kotlin.ui.second

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kg.geektech.kotlin.R
import kg.geektech.kotlin.databinding.ActivitySecondBinding
import kg.geektech.kotlin.ui.extensions.alert
import kg.geektech.kotlin.ui.extensions.invisible
import kg.geektech.kotlin.ui.extensions.toastText
import kg.geektech.kotlin.ui.extensions.visible
import kg.geektech.kotlin.ui.fullScreenActivity.FullScreenActivity
import kg.geektech.kotlin.ui.main.KEY_TEXT
import kg.geektech.kotlin.ui.second.rv.Adapter
import java.util.*

const val KEY_URL = "url_image"

class
SecondActivity : AppCompatActivity(), Adapter.ItemDeleteClick {
    private lateinit var binding: ActivitySecondBinding
    private var data: String? = ""
    private var newData: String? = ""
    private var link: String? = ""
    private var arrayOfPhoto: MutableList<String> = mutableListOf()
    private var array: MutableList<String> = mutableListOf()
    private lateinit var adapter: Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater);
        setContentView(binding.root)
     //binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(arrayOfPhoto, this)
        binding.recyclerView.apply {
            adapter = this@SecondActivity.adapter
        }
        arrayOfPhoto.apply {
        adapter.add("https://image.freepik.com/free-vector/cupid-boy-with-arrow_43623-578.jpg")
        adapter.add("https://image.freepik.com/free-vector/cute-little-fairy-with-beautiful-long-braided-hairstyle_96037-456.jpg")
        adapter.add("https://image.freepik.com/free-vector/little-fairy-sitting-mushroom_96037-466.jpg")
        adapter.add("https://image.freepik.com/free-vector/set-fairy-characters-its-silhouette-white-background_1308-47621.jpg")
        adapter.add("https://image.freepik.com/free-vector/set-unicorns-with-multi-colored-mane-tail_250613-104.jpg")}
        getIntentRes();
        returnResultAction()
        openContainer()
        addPhotoUrl()
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

    private fun openContainer() {
        binding.addFab.setOnClickListener { view ->
            binding.linearContainer.visible()
        }
    }

    private fun addPhotoUrl() {
        binding.addToGallery.setOnClickListener {
            link = binding.editTextUrl.text.toString()
            if (android.util.Patterns.WEB_URL.matcher(link).matches()) {
                adapter.add(link!!)
                adapter.notifyDataSetChanged()
                binding.linearContainer.invisible()
            } else
                toastText(this, "You should enter Http")
        }
    }


    override fun deleteClick(position: Int) {
        alert(R.string.dialogTitle, R.string.dialogMessage, R.string.yes, R.string.no){
            if (position>=1){
           //adapter.removeItem(position)
            if (adapter.removeItem(position)) {
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }
        }else{
            toastText(this, "You cant delete this photo")}
        }

    }

    override fun openInFullScreen(url: String, position: Int) {
        if (arrayOfPhoto.isEmpty())
            else {
            var  link=arrayOfPhoto.get(position)
            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra(KEY_URL, link)
            Log.e("TAG", "openInFullScreen: $url")
            startActivity(intent)




        }
    }




}