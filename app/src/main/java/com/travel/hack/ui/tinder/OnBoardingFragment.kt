package com.travel.hack.ui.tinder

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.travel.hack.R
import com.travel.hack.ui.common.BaseFragment
import com.travel.hack.viewmodel.OnBoardingViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.fragment_on_boarding.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_on_boarding

    private val viewModel: OnBoardingViewModel by viewModel()

    private val cardStackListener: CardStackListener by lazy {
        CardStackListenerImpl { position, direction ->
            if (direction == Direction.Right) {
                viewModel.listSelectedPlace.add(
                    adapter.items[position]
                )
            }
        }
    }

    private val adapter = ListDelegationAdapter(
        onBoardingAdapterDelegate()
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = CardStackLayoutManager(requireContext(), cardStackListener)
        layoutManager.setDirections(Direction.HORIZONTAL)

        rvCards.layoutManager = layoutManager
        rvCards.adapter = adapter

        viewModel.sightsLiveData.observe(viewLifecycleOwner, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
    }


}
