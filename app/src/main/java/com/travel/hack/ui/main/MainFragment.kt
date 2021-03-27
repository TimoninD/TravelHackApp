package com.travel.hack.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.travel.hack.R
import com.travel.hack.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_main


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCreatePath.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSightsFragment())
        }
    }
}