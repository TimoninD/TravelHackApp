package com.travel.hack.ui.username

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.travel.hack.R
import com.travel.hack.model.Prefs
import com.travel.hack.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_username.*
import org.koin.android.ext.android.inject

class UsernameFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_username

    private val prefs: Prefs by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignIn.setOnClickListener {
            if (etName.text.isNotBlank()) {
                prefs.name = etName.text.toString()
                findNavController().navigate(UsernameFragmentDirections.actionUsernameFragmentToOnBoardingFragment())
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.username_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}