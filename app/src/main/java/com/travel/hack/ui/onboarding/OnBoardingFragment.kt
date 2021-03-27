package com.travel.hack.ui.onboarding

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.travel.hack.R
import com.travel.hack.ui.common.BaseFragment
import com.travel.hack.viewmodel.OnBoardingViewModel
import com.yuyakaido.android.cardstackview.*
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
            if (adapter.items.size - 1 == position) {
                viewModel.saveSights()
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

        ibSkip.setOnClickListener {
            viewModel.saveSights()
        }

        ibClose.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()

            layoutManager.setSwipeAnimationSetting(setting)
            rvCards.swipe()
        }

        ibLike.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()

            layoutManager.setSwipeAnimationSetting(setting)
            viewModel.listSelectedPlace.add(
                adapter.items[layoutManager.topPosition]
            )
            rvCards.swipe()
        }


        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.sightsLiveData.observe(viewLifecycleOwner, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        viewModel.navigateToCity.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToCitiesFragment())
            }
        })
    }


}
