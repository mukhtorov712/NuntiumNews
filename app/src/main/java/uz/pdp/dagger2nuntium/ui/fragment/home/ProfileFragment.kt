package uz.pdp.dagger2nuntium.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.databinding.FragmentProfileBinding
import uz.pdp.dagger2nuntium.utils.MySharedPreference
import uz.pdp.dagger2nuntium.utils.ThemeHelper
import uz.pdp.dagger2nuntium.utils.navOptions


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private lateinit var mySharedPreference: MySharedPreference


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mySharedPreference = MySharedPreference(requireContext())


        binding.apply {
            switchButton.isChecked = mySharedPreference.getBool("isDark")

            switchButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    mySharedPreference.setBool("isDark", true)
                    ThemeHelper.applyTheme(ThemeHelper.darkMode)
                    switchButton.isChecked = true
                } else {
                    mySharedPreference.setBool("isDark", false)
                    ThemeHelper.applyTheme(ThemeHelper.lightMode)
                    switchButton.isChecked = false
                }
            }

            language.setOnClickListener {
                findNavController().navigate(
                    R.id.action_navigation_profile_to_languageFragment,
                    null,
                    navOptions()
                )
            }
        }
    }

}