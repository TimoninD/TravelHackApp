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
        Story(title = "Еда", image = "https://i.pinimg.com/originals/e7/8f/6d/e78f6d46292bcf9908921ddf84216a05.jpg"),
        Story(
            title = "События",
            image = "https://n1s2.hsmedia.ru/0c/84/62/0c84624aa76d50a55305fa5040755a62/620x429_1_e951f9b89399609452fac6926d302773@1200x830_0xac120003_8446140831608549183.jpg"
        ),
        Story(title = "Театры", image = "https://upload.wikimedia.org/wikipedia/commons/e/e8/Bolshoi_interior_2011.jpeg"),
        Story(
            title = "Кино",
            image = "https://st2.depositphotos.com/1001877/5641/i/600/depositphotos_56412491-stock-photo-video-movie-cinema-concept-retro.jpg"
        ),
    )

    private val feedList = listOf(
        FeedItem(
            image = "https://polsha4you.com/wp-content/uploads/2019/10/1q.png",
            title = "Концерт в Польше",
            description = "В Варшаве 28 марта выступит местная рок-группа с необычным и новым стилем музыки"
        ),
        FeedItem(
            image = "https://polsha4you.com/wp-content/uploads/2019/10/1q.png",
            title = "Концерт в Польше",
            description = "В Варшаве 28 марта выступит местная рок-группа с необычным и новым стилем музыки"
        ),
        FeedItem(
            image = "https://polsha4you.com/wp-content/uploads/2019/10/1q.png",
            title = "Концерт в Польше",
            description = "В Варшаве 28 марта выступит местная рок-группа с необычным и новым стилем музыки"
        ),
        FeedItem(
            image = "https://polsha4you.com/wp-content/uploads/2019/10/1q.png",
            title = "Концерт в Польше",
            description = "В Варшаве 28 марта выступит местная рок-группа с необычным и новым стилем музыки"
        )
    )
}