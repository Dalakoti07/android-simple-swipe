package com.dalakoti07.android.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dalakoti07.android.R
import kotlinx.android.synthetic.main.fragment_sample.*

class SampleFragment: Fragment(R.layout.fragment_sample) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_action.setOnClickListener {
            findNavController().navigate(
                R.id.action_sampleFragment_to_sampleBottomSheet
            )
        }
    }

}