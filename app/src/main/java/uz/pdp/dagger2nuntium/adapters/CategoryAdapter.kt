package uz.pdp.dagger2nuntium.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yariksoffice.lingver.Lingver
import uz.pdp.dagger2nuntium.databinding.ItemCategoryBinding
import uz.pdp.dagger2nuntium.utils.getCatListWith

class CategoryAdapter(
    private val list: List<Boolean>,
    var onItemClickListener: OnItemClickListener,
) :
    RecyclerView.Adapter<CategoryAdapter.Vh>() {

    private val categoryList = getCatListWith(Lingver.getInstance().getLanguage())

    inner class Vh(var itemCategoryBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemCategoryBinding.root) {

        fun onBind(str: String, bool: Boolean, position: Int) {
            itemCategoryBinding.apply {
                text.text = str
                if (!bool) {
                    text.setTextColor(Color.parseColor("#666C8E"))
                    layout.setCardBackgroundColor(Color.parseColor("#F3F4F6"))
                } else {
                    text.setTextColor(Color.parseColor("#FFFFFF"))
                    layout.setCardBackgroundColor(Color.parseColor("#475AD7"))
                }
                layout.setOnClickListener {
                    onItemClickListener.onItemClick(bool, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(categoryList[position],list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onItemClick(bool: Boolean, position: Int)
    }
}