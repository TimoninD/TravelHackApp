package com.travel.hack.ui.cities

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.travel.hack.R
import com.travel.hack.entity.core.City
import com.travel.hack.ext.convertDpToPx
import kotlinx.android.synthetic.main.item_city.view.*

private const val RADIUS = 16f

private var selectedPosition = RecyclerView.NO_POSITION
fun citiesAdapterDelegate(itemClickListener: (city: City, selectedPos: Int, adapterPos: Int) -> Unit) =
    adapterDelegate<City, City>(R.layout.item_city) {

        itemView.setOnClickListener {
            itemClickListener.invoke(item, selectedPosition, adapterPosition)
            selectedPosition = if (selectedPosition == adapterPosition) -1 else adapterPosition
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
                item.isSelected = selectedPosition == adapterPosition
                viewSelected.isVisible = item.isSelected
                tvCity.text = item.name
            }
        }
    }