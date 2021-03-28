package com.travel.hack.ui.cities

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.travel.hack.R
import com.travel.hack.entity.core.City
import com.travel.hack.model.Prefs
import com.travel.hack.ui.common.BaseFragment
import com.travel.hack.util.GridSpacingItemDecoration
import com.travel.hack.viewmodel.CitiesViewModel
import kotlinx.android.synthetic.main.fragment_cities.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_cities

    private val adapter: ListDelegationAdapter<List<City>> by lazy {
        ListDelegationAdapter(
            citiesAdapterDelegate { city, selectedPos, adapterPos ->
                if (selectedPos != adapterPos && selectedPos != RecyclerView.NO_POSITION) {
                    adapter.notifyItemChanged(selectedPos)
                }
                adapter.notifyItemChanged(adapterPos)
                city.isSelected = adapterPos != selectedPos
                viewModel.selectedCityId = if (city.isSelected) city.id else null

                btnNext.isVisible = viewModel.selectedCityId != null
            }
        )
    }

    private val viewModel: CitiesViewModel by viewModel()

    private val prefs: Prefs by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.isVisible = viewModel.selectedCityId != null

        rvCities.addItemDecoration(
            GridSpacingItemDecoration(
                SPAN_COUNT,
                SPACE_BETWEEN_COLUMN,
                true
            )
        )
        rvCities.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        rvCities.adapter = adapter

        viewModel.cities.observe(viewLifecycleOwner, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        btnNext.setOnClickListener {
            prefs.cityId = viewModel.selectedCityId ?: 0
            findNavController().navigate(CitiesFragmentDirections.actionCitiesFragmentToMainFragment())
        }
    }

    companion object {
        private const val SPAN_COUNT = 3
        private const val SPACE_BETWEEN_COLUMN = 8
    }
}