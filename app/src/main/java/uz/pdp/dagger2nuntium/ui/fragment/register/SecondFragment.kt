package uz.pdp.dagger2nuntium.ui.fragment.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val binding by viewBinding(FragmentSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getBtn.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

    }
}