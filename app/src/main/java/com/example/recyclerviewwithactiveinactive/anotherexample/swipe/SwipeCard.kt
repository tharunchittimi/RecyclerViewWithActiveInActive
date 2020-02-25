package com.example.recyclerviewwithactiveinactive.anotherexample.swipe

import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithactiveinactive.R
import com.example.recyclerviewwithactiveinactive.anotherexample.DummyRecycleViewAdapter
import com.example.recyclerviewwithactiveinactive.application.MyApplication
import com.google.android.material.snackbar.Snackbar

class SwipeCard : ItemTouchHelper.SimpleCallback {

    private lateinit var dummyRecycleViewAdapter: DummyRecycleViewAdapter
    private lateinit var dummyRvCard: RecyclerView

    constructor(dragDirs: Int, swipeDirs: Int) : super(dragDirs, swipeDirs)

    constructor(adapter: DummyRecycleViewAdapter) : super(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
        this.dummyRecycleViewAdapter = adapter
    }

    constructor(dragDirs: DummyRecycleViewAdapter, swipeDirs: RecyclerView?) : super(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT){
        this.dummyRecycleViewAdapter = dragDirs
        this.dummyRvCard= swipeDirs!!
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.layoutPosition
        val item = dummyRecycleViewAdapter.getData()[position]
        dummyRecycleViewAdapter.dismissRvPosition(position)
        val snackbar = Snackbar
            .make(viewHolder.itemView.findViewById(R.id.dummyCardItem), "SnackBar with Ok button", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                                dummyRecycleViewAdapter.restoreRvPosition(position,item)
                dummyRvCard.scrollToPosition(position)
                Toast.makeText(MyApplication.getApplicationContext(),"Removed Item Inserted at $position",Toast.LENGTH_LONG).show()

            }
        snackbar.show()
        Toast.makeText(MyApplication.getApplicationContext(),"removed at : ${viewHolder.layoutPosition}",Toast.LENGTH_LONG).show()
    }
}