package com.example.recyclerviewwithactiveinactive.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewwithactiveinactive.R
import com.example.recyclerviewwithactiveinactive.base.BaseActivity
import kotlinx.android.synthetic.main.activity_recycleview_class.*

class RecyclerViewClass : BaseActivity(){

     private var recyclerViewAdapter: RecyclerViewAdapter? = null
    override fun setLayout(): Int {
        return R.layout.activity_recycleview_class
    }

    override fun initView(savedInstanceState: Bundle?) {
         val recyclerViewModel: ArrayList<RecyclerViewModel>  = ArrayList()
        rvCard.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(recyclerViewModel)
        rvCard.adapter = recyclerViewAdapter

        additem.setOnClickListener {
            recyclerViewModel.add(RecyclerViewModel())
            recyclerViewAdapter?.notifyItemInserted(recyclerViewModel.size-1)
        }
        removeItem.setOnClickListener {
            recyclerViewModel.removeAt(recyclerViewModel.size-1)
            recyclerViewAdapter?.notifyItemRemoved(recyclerViewModel.size)
        }
    }
}