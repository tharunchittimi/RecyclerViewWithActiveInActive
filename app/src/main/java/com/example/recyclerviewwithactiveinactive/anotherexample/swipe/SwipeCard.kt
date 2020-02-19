package com.example.recyclerviewwithactiveinactive.anotherexample.swipe

import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithactiveinactive.anotherexample.DummyRecycleViewAdapter
import com.example.recyclerviewwithactiveinactive.application.MyApplication

class SwipeCard : ItemTouchHelper.SimpleCallback {

    private lateinit var dummyRecycleViewAdapter: DummyRecycleViewAdapter

    constructor(dragDirs: Int, swipeDirs: Int) : super(dragDirs, swipeDirs)

    constructor(adapter: DummyRecycleViewAdapter) : super(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
        this.dummyRecycleViewAdapter = adapter
    }
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        dummyRecycleViewAdapter.dismissRvPosition(viewHolder.adapterPosition)
        Toast.makeText(MyApplication.getApplicationContext(),"removed at : ${viewHolder.adapterPosition}",Toast.LENGTH_LONG).show()
    }
}