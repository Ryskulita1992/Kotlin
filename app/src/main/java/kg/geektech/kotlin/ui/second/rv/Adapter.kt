package kg.geektech.kotlin.ui.second.rv

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlin.R
import kg.geektech.kotlin.databinding.ItemImagesBinding
import kg.geektech.kotlin.ui.extensions.inflate
import kg.geektech.kotlin.ui.extensions.loadImage
import kg.geektech.kotlin.ui.extensions.toastText
import kotlinx.android.synthetic.main.item_images.view.*
import java.util.*


class Adapter(
    private var photoList: MutableList<String>, private var onDeleteClick: ItemDeleteClick
) :
    RecyclerView.Adapter<Adapter.PhotoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val v = parent.inflate(R.layout.item_images)
        return PhotoHolder(v, onDeleteClick)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photoList[position])


    }

    fun add(list: String) {
        photoList.add(list)
        notifyDataSetChanged()

    }

    fun removeItem(position: Int): Boolean {
        if (photoList.size >= position + 1) {
            photoList.removeAt(position)
            return true
        }
        return false
    }

    inner class PhotoHolder(v: View, onItemView: ItemDeleteClick) : RecyclerView.ViewHolder(v) {
        val binding = ItemImagesBinding.bind(v)

        init {
            itemView.setOnClickListener {
                onItemView.openInFullScreen(adapterPosition.toString(),  position )

            }
            itemView.setOnLongClickListener{
                onItemView.deleteClick(adapterPosition)
                true
            }        }

        fun bind(url: String) {
            binding.itemImageRv.item_image_rv.loadImage(url)


        }
    }


    interface ItemDeleteClick {
        fun deleteClick(position: Int)
        fun openInFullScreen(url: String, position: Int)
    }

}
