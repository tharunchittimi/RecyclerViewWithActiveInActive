package com.example.recyclerviewwithactiveinactive.anotherexample

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewwithactiveinactive.R
import com.example.recyclerviewwithactiveinactive.base.BaseActivity
import kotlinx.android.synthetic.main.activity_dummy_recycleview_class.*
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recyclerviewwithactiveinactive.anotherexample.swipe.SwipeCard
import com.google.android.material.snackbar.Snackbar


class DummyRecycleViewClass:BaseActivity(),DummyRecycleViewAdapter.DismissRvPosition {

    override fun dismissRvPositionIn(pos: Int) {
        dummyRecycleViewModel?.removeAt(pos)
        dummyRecycleViewAdapter?.notifyItemRemoved(pos)
        dummyRecycleViewAdapter?.notifyItemRangeChanged(pos, dummyRecycleViewModel!!.size)
    }

    private var dummyRecycleViewModel: ArrayList<DummyRecycleViewModel>? = null
    private var dummyRecycleViewAdapter: DummyRecycleViewAdapter? = null
    override fun setLayout(): Int {
        return R.layout.activity_dummy_recycleview_class
    }

    override fun initView(savedInstanceState: Bundle?) {
        setList()
        fab.setOnClickListener {
            Toast.makeText(this,"size is : ${dummyRecycleViewModel?.size}" ,Toast.LENGTH_LONG).show()
        }

        dummyRvCard.layoutManager = LinearLayoutManager(this)
        dummyRecycleViewAdapter = DummyRecycleViewAdapter(dummyRecycleViewModel?: ArrayList(),this)
        dummyRvCard.adapter = dummyRecycleViewAdapter

        val callback = SwipeCard(dummyRecycleViewAdapter!!)
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(dummyRvCard)
    }

    private fun setList() {
        dummyRecycleViewModel= ArrayList()
        for (i in 0..15){
            dummyRecycleViewModel?.add(DummyRecycleViewModel())
        }
    }
}