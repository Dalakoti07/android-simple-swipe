package com.dalakoti07.android.frags

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dalakoti07.android.R
import kotlinx.android.synthetic.main.fragment_sample.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SampleFragment : Fragment(R.layout.fragment_sample) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_action.setOnClickListener {
            findNavController().navigate(
                R.id.action_sampleFragment_to_sampleBottomSheet
            )
        }
        findNavController().currentBackStackEntry
            ?.savedStateHandle?.getLiveData<Boolean>("ORDER_ITEMS_ARGUMENT_KEY")
            ?.observe(viewLifecycleOwner) {
                Log.d("SampleFragment", "boolean: $it ")
                if (it) {
                    findNavController().navigate(
                        R.id.action_sampleFragment_to_sampleDialogFragment
                    )
                    // reset the back-stack entry
                    findNavController().currentBackStackEntry
                        ?.savedStateHandle?.set("ORDER_ITEMS_ARGUMENT_KEY", false)
                }
            }
    }

}