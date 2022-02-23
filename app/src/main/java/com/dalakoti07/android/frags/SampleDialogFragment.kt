package com.dalakoti07.android.frags

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.dalakoti07.android.R
import kotlinx.android.synthetic.main.fragment_dialog.*

class SampleDialogFragment : DialogFragment(R.layout.fragment_dialog){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_close_dialog.setOnClickListener {
            dismiss()
        }
    }

}