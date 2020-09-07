package com.example.recyclerviewwithactiveinactive.anotherexample

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewwithactiveinactive.R
import com.example.recyclerviewwithactiveinactive.anotherexample.swipe.SwipeCard
import com.example.recyclerviewwithactiveinactive.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dummy_recycleview_class.*


class DummyRecycleViewClass:BaseActivity(),DummyRecycleViewAdapter.DismissRvPosition {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun dismissRvPositionIn(pos: Int, id: Int?) {
//        dummyRecycleViewAdapter?.modifyDentalCareItem(DummyRecycleViewModel(3))
//        Toast.makeText(this,"replaced: $id",Toast.LENGTH_LONG).show()
        val alertDialog=AlertDialog.Builder(this)
        alertDialog.setIcon(R.drawable.ic_add_alert)
            .setTitle("Are you sure want to Delete!")
            .setPositiveButton("Delete") { dialogInterface: DialogInterface, i: Int ->
                val item = dummyRecycleViewAdapter!!.getData().get(pos)
                dummyRecycleViewModel?.removeAt(pos)
                dummyRecycleViewAdapter?.notifyItemRemoved(pos)
                dummyRecycleViewAdapter?.notifyItemRangeChanged(pos, dummyRecycleViewModel!!.size)
                dummyRecycleViewAdapter?.notifyDataSetChanged()

                Toast.makeText(this,"id at : $id",Toast.LENGTH_LONG).show()
                showSnackBar(pos,item)
            }
            .setNegativeButton("Cancel")  {dialogInterface: DialogInterface, i: Int-> }
            .show()
    }

    private fun showSnackBar(position:Int, item:DummyRecycleViewModel) {
        val snackbar = Snackbar
            .make(findViewById(R.id.llvRoot), "SnackBar with Ok button", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                dummyRecycleViewAdapter?.restoreRvPosition(position,item )
                dummyRvCard.scrollToPosition(position)
                Toast.makeText(this,"Removed Item Inserted at : $position",Toast.LENGTH_LONG).show()
            }
        snackbar.show()
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

        val callback = SwipeCard(dummyRecycleViewAdapter!!, dummyRvCard)
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(dummyRvCard)
    }

    private fun setList() {
        var count = 0
        dummyRecycleViewModel= ArrayList()
        for (i in 0..15){
            count++
            dummyRecycleViewModel?.add(DummyRecycleViewModel(count))
        }
    }
}