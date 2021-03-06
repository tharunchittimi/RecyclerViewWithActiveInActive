package com.example.recyclerviewwithactiveinactive.anotherexample

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithactiveinactive.R
import com.example.recyclerviewwithactiveinactive.application.MyApplication


class DummyRecycleViewAdapter(private val dummyRecycleViewModel: ArrayList<DummyRecycleViewModel>,var context: Context) :
    RecyclerView.Adapter<DummyRecycleViewAdapter.DummyRecycleViewHolder>() {
    private var count=0
    private var bindingCount=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyRecycleViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(com.example.recyclerviewwithactiveinactive.R.layout.dummy_card_rv, parent, false)
        count++
        Log.d("testing","onCreateViewHolder$count")
        return DummyRecycleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dummyRecycleViewModel.size
    }

    override fun onBindViewHolder(holder: DummyRecycleViewHolder, position: Int) {
        when(dummyRecycleViewModel[position].isChecked){
            null->{
                holder.acceptButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.rejectButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.card.backgroundTintList =
                ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.textReq.visibility=View.GONE
            }
            true->{
                holder.acceptButton.backgroundTintList =
                ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.green)
                holder.rejectButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.card.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.green)
                holder.textReq.visibility=View.VISIBLE
                holder.textReq.text="Accepted"

            }
            false->{
                holder.rejectButton.backgroundTintList =
                ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.red)
                holder.acceptButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.card.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.red)
                holder.textReq.visibility=View.VISIBLE
                holder.textReq.text="Rejected"

            }
        }

        holder.acceptButton.setOnClickListener {
            if (dummyRecycleViewModel[position].isChecked!=true){
                dummyRecycleViewModel[position].isChecked=true
                notifyItemChanged(position)
            }
        }
        holder.rejectButton.setOnClickListener {
            if (dummyRecycleViewModel[position].isChecked!=false) {
                dummyRecycleViewModel[position].isChecked = false
                notifyItemChanged(position)
            }
        }
        holder.card.setOnClickListener {
            if (dummyRecycleViewModel[position].isChecked!=null) {
                dummyRecycleViewModel[position].isChecked = null
                notifyItemChanged(position)
            }
        }

        bindingCount++
        Log.d("testing","onBindViewHolder$bindingCount")

        holder.itemView.setOnLongClickListener {
          val  dismissRvPosition = context as DismissRvPosition
            dismissRvPosition.dismissRvPositionIn(position, dummyRecycleViewModel[position].id)
            return@setOnLongClickListener true
        }
    }

    class DummyRecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.dummyCardItem)
        val acceptButton: Button = itemView.findViewById(R.id.dummyAcceptBtn)
        val rejectButton: Button = itemView.findViewById(R.id.dummyRejectBtn)
        val textReq: TextView = itemView.findViewById(R.id.dummyRequestText)
    }

    interface DismissRvPosition{
        fun dismissRvPositionIn(pos: Int, id: Int?)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun modifyDentalCareItem(data : DummyRecycleViewModel) {
        dummyRecycleViewModel.removeIf { it.id == data.id }
        dummyRecycleViewModel.add(0, data)
        notifyItemMoved(data.id ?:0, 0)
        notifyItemRangeChanged(0, dummyRecycleViewModel.size)
    }

    fun dismissRvPosition(pos: Int) {
        dummyRecycleViewModel.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos, dummyRecycleViewModel.size)
    }

    fun restoreRvPosition(pos: Int,item:DummyRecycleViewModel){
        dummyRecycleViewModel.add(pos,item)
        notifyItemInserted(pos)
        notifyItemRangeChanged(pos, dummyRecycleViewModel.size)
    }

    fun getData(): ArrayList<DummyRecycleViewModel> {
        return dummyRecycleViewModel
    }
}