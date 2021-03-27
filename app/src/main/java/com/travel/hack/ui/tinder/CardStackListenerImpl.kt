package com.travel.hack.ui.tinder

import android.util.Log
import android.view.View
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class CardStackListenerImpl(private val onCardDisappearedListener: (Int, Direction?) -> Unit) :
    CardStackListener {

    private var lastDirection: Direction? = null
    override fun onCardDragging(direction: Direction?, ratio: Float) {
        lastDirection = direction
    }

    override fun onCardSwiped(direction: Direction?) {
        lastDirection = direction
    }

    override fun onCardRewound() {
        Log.e("Tag", "onCardRewound")
    }

    override fun onCardCanceled() {
        Log.e("Tag", "onCardCanceled")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.e("Tag", "onCardAppeared")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.e("Tag", "onCardDisappeared")
        onCardDisappearedListener(position, lastDirection)
    }
}