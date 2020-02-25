package com.example.recyclerviewwithactiveinactive.anotherexample

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewwithactiveinactive.R
import com.example.recyclerviewwithactiveinactive.anotherexample.swipe.SwipeCard
import com.example.recyclerviewwithactiveinactive.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dummy_recycleview_class.*


class DummyRecycleViewClass:BaseActivity(),DummyRecycleViewAdapter.DismissRvPosition {

    override fun dismissRvPositionIn(pos: Int) {
        val alertDialog=AlertDialog.Builder(this)
        alertDialog.setIcon(R.drawable.ic_add_alert)
            .setTitle("Are you sure want to Delete!")
            .setPositiveButton("Delete") { dialogInterface: DialogInterface, i: Int ->
                val  position =pos
                val item = dummyRecycleViewAdapter!!.getData().get(pos)
                dummyRecycleViewModel?.removeAt(pos)
                dummyRecycleViewAdapter?.notifyItemRemoved(pos)
                dummyRecycleViewAdapter?.notifyItemRangeChanged(pos, dummyRecycleViewModel!!.size)

                Toast.makeText(this,"removed at : $pos",Toast.LENGTH_LONG).show()
                showSnackBar(position,item)
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
        dummyRecycleViewModel= ArrayList()
        for (i in 0..15){
            dummyRecycleViewModel?.add(DummyRecycleViewModel())
        }
    }
}