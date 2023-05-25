package com.example.khatabook

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.khatabook.databinding.TableBinding


class TransactionlistAdapter(list: ArrayList<listModal>) :
    RecyclerView.Adapter<TransactionlistAdapter.listHolader>() {
    var list = list


    class listHolader(itemView: TableBinding) : ViewHolder(itemView.root) {
        var binding = itemView

//        var id = binding.txtid
//        var amount = binding.txtamount
//        var title = binding.txttitle
//        var note = binding.txtnote
//        var isExpanse = binding.idbg
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listHolader {
        var binding = TableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listHolader(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: listHolader, position: Int) {
//        holder.id.text = list.get(position).id.toString()
//        holder.amount.text = list.get(position).amount.toString()
//        holder.title.text = list.get(position).title
//        holder.note.text = list.get(position).note
//        holder.isExpanse.te
//
//        if (isExpanse == 0) {
//            holder.binding.txtamount.setTextColor(Color.GREEN)
//        }

        holder.binding.apply {
            list.get(position).apply {
                txtid.text = id.toString()
                txtamount.text = amount.toString()
                txtnote.text = note
                txttitle.text = title

                if (isExpense == 0) {
                    idbg2.setImageResource(R.drawable.bg3)
                    idbg1.setImageResource(R.drawable.bg5)
                } else {
                    txtamount.setTextColor(Color.RED)
                    idbg2.setImageResource(R.drawable.bg2)
                    idbg1.setImageResource(R.drawable.bg4)
                }
            }
        }

    }

    fun update(get: ArrayList<listModal>) {
        list = get
        notifyDataSetChanged()
    }
}