package com.travel.hack.ui.sights

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.travel.hack.R
import com.travel.hack.entity.core.Place
import com.travel.hack.ext.convertDpToPx
import kotlinx.android.synthetic.main.item_sights.view.*

private const val RADIUS = 16f
fun sightsAdapterDelegate(itemClickListener: (place: Place, position: Int) -> Unit) =
    adapterDelegate<Place, Place>(R.layout.item_sights) {

        itemView.setOnClickListener {
            item.isSelected = !item.isSelected
            itemClickListener.invoke(item, adapterPosition)
        }

        bind {
            with(itemView) {
                Glide.with(context)
                    .load(item.image)
                    .placeholder(R.drawable.preview_square)
                    .apply {
                        transform(
                            CenterCrop(),
                            RoundedCorners(context.convertDpToPx(RADIUS).toInt())
                        )
                    }
                    .into(ivImage)

                tvTitle.text = item.title
                tvDescription.text = item.description
                ivChecked.isInvisible = !item.isSelected
            }
        }

    }