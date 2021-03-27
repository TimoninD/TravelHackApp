package com.travel.hack.ui.main

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.travel.hack.R
import com.travel.hack.entity.core.FeedItem
import com.travel.hack.ext.convertDpToPx
import kotlinx.android.synthetic.main.item_feed.view.*

private const val RADIUS = 24f

fun feedAdapterDelegate() = adapterDelegate<FeedItem, FeedItem>(R.layout.item_feed) {

    itemView.setOnClickListener {

    }

    bind {
        with(itemView) {
            Glide.with(context)
                .load(item.image)
                .placeholder(R.drawable.bg_feed_placeholder)
                .apply {
                    val radius = context.convertDpToPx(RADIUS)
                    transform(CenterCrop(), GranularRoundedCorners(radius, radius, 0f, 0f))
                }
                .into(ivFeed)

            tvTitle.text = item.title
            tvDescription.text = item.description
        }
    }
}