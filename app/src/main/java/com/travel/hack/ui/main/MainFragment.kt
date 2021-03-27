package com.travel.hack.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.travel.hack.R
import com.travel.hack.entity.core.FeedItem
import com.travel.hack.entity.core.Story
import com.travel.hack.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_main

    private val storiesAdapter = ListDelegationAdapter(
        storiesAdapterDelegate()
    )

    private val feedAdapter = ListDelegationAdapter(
        feedAdapterDelegate()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        rvFeed.layoutManager = layoutManager
        rvFeed.adapter = feedAdapter

        rvStories.adapter = storiesAdapter

        storiesAdapter.items = storiesList
        storiesAdapter.notifyDataSetChanged()

        feedAdapter.items = feedList
        feedAdapter.notifyDataSetChanged()

        btnCreateRoute.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSightsFragment())
        }
    }

    private val storiesList = listOf(
        Story(title = "Мексика", image = "https://www.jettravel.ru/upload/iblock/db2/6-66.jpg"),
        Story(
            title = "Италия",
            image = "https://traveller-eu.ru/sites/default/files/styles/index/public/italy-4093227_1280.jpg"
        ),
        Story(title = "Швеция", image = "https://files.tpg.ua/pages2/87892/sweden.jpg"),
        Story(
            title = "Россия",
            image = "https://pptravel.ru/sites/default/files/files/countries/Russia/russia_photo_1.jpg"
        ),
        Story(
            title = "Аргентина",
            image = "https://cms.enjourney.ru/upload/Jana/Argentina/Argentina12.jpg"
        )
    )

    private val feedList = listOf(
        FeedItem(
            image = "https://polsha4you.com/wp-content/uploads/2019/10/1q.png",
            title = "Концерт в Польше",
            description = "В Варшаве 28 марта выступит местная рок-группа с необычным и новым стилем музыки"
        ),
        FeedItem(image = "", title = "", description = ""),
        FeedItem(image = "", title = "", description = ""),
        FeedItem(image = "", title = "", description = "")
    )
}