package uz.pdp.dagger2nuntium.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.adapters.CategoryAdapter
import uz.pdp.dagger2nuntium.databinding.FragmentMenuBinding
import uz.pdp.dagger2nuntium.utils.MySharedPreference


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)
    lateinit var categoryAdapter: CategoryAdapter
    private lateinit var mySharedPreference: MySharedPreference
    private lateinit var boolList:ArrayList<Boolean>
    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedPreference = MySharedPreference(requireContext())
        boolList = ArrayList(mySharedPreference.getBoolList())
        categoryAdapter = CategoryAdapter(boolList, object
            : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(bool: Boolean, position: Int) {
                boolList[position] = !bool
                categoryAdapter.notifyItemChanged(position)
            }
        })

        binding.apply {
            rv.adapter = categoryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        boolList.forEach {
            if (it) {
                count++
            }
        }
        if (count != 0) {
            mySharedPreference.setBoolList(boolList)
            mySharedPreference.setRegister()
        } else {
            Toast.makeText(
                requireContext(),
                "Select at least 1 category!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}