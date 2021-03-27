package com.travel.hack.ui.main

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.travel.hack.R
import com.travel.hack.entity.core.Story
import kotlinx.android.synthetic.main.item_story.view.*

fun storiesAdapterDelegate() = adapterDelegate<Story, Story>(R.layout.item_story) {

    bind {
        with(itemView) {
            Glide.with(context)
                .load(item.image)
                .circleCrop()
                .into(ivStory)

            tvTitle.text = item.title
        }
    }
}
