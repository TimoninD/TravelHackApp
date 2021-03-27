package com.travel.hack.ui.sights

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.travel.hack.R
import com.travel.hack.entity.core.Place
import com.travel.hack.ui.common.BaseFragment
import com.travel.hack.viewmodel.SightsViewModel
import kotlinx.android.synthetic.main.fragment_sights.*
import org.koin.android.ext.android.inject

class SightsFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_sights

    private val viewModel: SightsViewModel by inject()

    private val adapter: ListDelegationAdapter<List<Place>> by lazy {
        ListDelegationAdapter(
            sightsAdapterDelegate { place, position ->
                if (place.isSelected) {
                    viewModel.selectedSightsId.add(place.id)
                } else {
                    viewModel.selectedSightsId.remove(place.id)
                }
                btnNext.isVisible = viewModel.selectedSightsId.isNotEmpty()
                adapter.notifyItemChanged(position)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSights.adapter = adapter

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.sights.observe(viewLifecycleOwner, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
    }
}