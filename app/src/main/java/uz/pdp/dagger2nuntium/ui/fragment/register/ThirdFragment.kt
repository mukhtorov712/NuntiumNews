package uz.pdp.dagger2nuntium.ui.fragment.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.gson.Gson
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.adapters.CategoryAdapter
import uz.pdp.dagger2nuntium.databinding.FragmentThirdBinding
import uz.pdp.dagger2nuntium.ui.activity.HomeActivity
import uz.pdp.dagger2nuntium.utils.MySharedPreference


class ThirdFragment : Fragment(R.layout.fragment_third) {
    private val binding by viewBinding(FragmentThirdBinding::bind)
    private lateinit var mySharedPreference: MySharedPreference
    private lateinit var categoryAdapter: CategoryAdapter
    private var boolList = arrayListOf(false, false, false, false, false, false)
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mySharedPreference = MySharedPreference(requireContext())
        categoryAdapter = CategoryAdapter(boolList, object
            : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(bool: Boolean, position: Int) {
                boolList[position] = !bool
                categoryAdapter.notifyItemChanged(position)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rv.adapter = categoryAdapter
            getBtn.setOnClickListener {
                boolList.forEach {
                    if (it) {
                        count++
                    }
                }
                if (count != 0) {
                    mySharedPreference.setBoolList(boolList)
                    mySharedPreference.setRegister()
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Select category!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

}