package com.travel.hack.ui.onboarding

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.travel.hack.R
import com.travel.hack.entity.core.Place
import com.travel.hack.ext.convertDpToPx
import kotlinx.android.synthetic.main.item_on_boarding.view.*

private const val RADIUS = 20
fun onBoardingAdapterDelegate() = adapterDelegate<Place, Place>(R.layout.item_on_boarding) {
    bind {
        itemView.tvTitle.text = item.title
        itemView.tvDescription.text = item.description
        Glide.with(context)
            .load(item.image)
            .placeholder(R.drawable.bg_placeholder)
            .apply {
                transform(
                    CenterCrop(),
                    RoundedCorners(context.convertDpToPx(RADIUS.toFloat()).toInt())
                )
            }
            .into(itemView.ivImage)
    }
}