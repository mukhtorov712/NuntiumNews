package uz.pdp.dagger2nuntium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.pdp.dagger2nuntium.databinding.ItemCarouselBinding

class CarouselAdapter(private val list: ArrayList<String>) :
    RecyclerView.Adapter<CarouselAdapter.Vh>() {

    inner class Vh(private val itemCarousel: ItemCarouselBinding) :
        RecyclerView.ViewHolder(itemCarousel.root) {

        fun onBind(string: String) {
            itemCarousel.apply {
                Picasso.get().load(string).into(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}