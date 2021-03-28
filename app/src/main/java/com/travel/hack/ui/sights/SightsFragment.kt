package com.travel.hack.ui.sights

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationServices
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.travel.hack.R
import com.travel.hack.entity.core.Place
import com.travel.hack.entity.core.PlaceIdList
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
                tvTitle.text = getString(R.string.sights_count, viewModel.selectedSightsId.size)
                btnNext.isVisible = viewModel.selectedSightsId.isNotEmpty()
                adapter.notifyItemChanged(position)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSights.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rvSights.adapter = adapter

        btnNext.setOnClickListener {
            findNavController().navigate(
                SightsFragmentDirections.actionSightsFragmentToMapFragment(
                    PlaceIdList(viewModel.selectedSightsId)
                )
            )
        }

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.sights.observe(viewLifecycleOwner, {
            tvTitle.text = getString(R.string.sights_count, it.size)

            adapter.items = it
            adapter.notifyDataSetChanged()
        })
    }
}