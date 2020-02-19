package com.example.recyclerviewwithactiveinactive.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithactiveinactive.R
import com.example.recyclerviewwithactiveinactive.application.MyApplication

class RecyclerViewAdapter(private val recyclerViewModel: ArrayList<RecyclerViewModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_rv, parent, false)
        return RecycleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recyclerViewModel.size
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        when (recyclerViewModel[position].uiStatus) {
            RecyclerViewModel.Status.DEFAULT -> {
               holder.card.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.white)
                holder. acceptButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(),R.color.lightGrey)
                holder.   rejectButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.textReq.visibility=View.GONE
            }
            RecyclerViewModel.Status.ACCEPT -> {
                holder. card.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.green)
                holder.acceptButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.green)
                holder.rejectButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.textReq.visibility=View.VISIBLE
                holder.textReq.text = MyApplication.getApplicationContext().getString(R.string.accepted)
            }
            RecyclerViewModel.Status.REJECT -> {
                holder.card.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.red)
                holder.acceptButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.lightGrey)
                holder.rejectButton.backgroundTintList =
                    ContextCompat.getColorStateList(MyApplication.getApplicationContext(), R.color.red)
                holder.textReq.visibility=View.VISIBLE
                holder.textReq.text = MyApplication.getApplicationContext().getString(R.string.rejected)
            }
        }
        holder.acceptButton.setOnClickListener {
            if ( recyclerViewModel[position].uiStatus!=RecyclerViewModel.Status.ACCEPT){
                recyclerViewModel[position].uiStatus=RecyclerViewModel.Status.ACCEPT
                notifyItemChanged(position)
            }
        }

        holder.rejectButton.setOnClickListener {
            if (recyclerViewModel[position].uiStatus!=RecyclerViewModel.Status.REJECT) {
                recyclerViewModel[position].uiStatus = RecyclerViewModel.Status.REJECT
                notifyItemChanged(position)
            }
        }

        holder.card.setOnClickListener {
            if (recyclerViewModel[position].uiStatus!=RecyclerViewModel.Status.DEFAULT) {
                recyclerViewModel[position].uiStatus = RecyclerViewModel.Status.DEFAULT
                notifyItemChanged(position)
            }
        }
    }

    class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val card: CardView = itemView.findViewById(R.id.cardItem)
         val acceptButton: Button = itemView.findViewById(R.id.acceptBtn)
         val rejectButton: Button = itemView.findViewById(R.id.rejectBtn)
        val textReq:TextView=itemView.findViewById(R.id.requestText)
    }
}
