package com.example.khatabook

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.khatabook.databinding.TableBinding


class TransactionlistAdapter(
    updata: (listModal) -> Unit,
    list: ArrayList<listModal>,
    Delete: (Int) -> Unit,
    info: (listModal) -> Unit
) :
    RecyclerView.Adapter<TransactionlistAdapter.listHolader>() {
    var list = list
    lateinit var context: Context
    var updata = updata
    var info = info
    var delete = Delete

    class listHolader(itemView: TableBinding) : ViewHolder(itemView.root) {
        var binding = itemView


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listHolader {
        context = parent.context
        var binding = TableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listHolader(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(
        holder: listHolader,
        @SuppressLint("RecyclerView") position: Int
    ) {


        holder.binding.apply {
            list.get(position).apply {
                txtid.text = id.toString()
                txtamount.text = amount.toString()
                txtnote.text = note
                txttitle.text = title


                if (isExpense == 0) {
                    idbg2.setImageResource(R.drawable.bg3)
                    idbg1.setImageResource(R.drawable.bg5)
                    total(amount)
                } else {
                    txtamount.setTextColor(Color.RED)
                    idbg2.setImageResource(R.drawable.bg2)
                    idbg1.setImageResource(R.drawable.bg4)
                    total(amount)
                }

            }
        }

        holder.itemView.setOnLongClickListener(object : OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {

                var popupMenu = PopupMenu(context, holder.itemView)
                popupMenu.menuInflater.inflate(R.menu.delete, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        if (p0?.itemId == R.id.update) {
                            updata.invoke(list.get(position))
                        }

                        if (p0?.itemId == R.id.delete) {
                            delete.invoke(list.get(position).id)
                        }

                        if(p0?.itemId == R.id.info)
                        {
                            info.invoke(list.get(position))
                        }
                        return true
                    }
                })
                popupMenu.show()
                return true
            }
        })


    }
    fun update(get: ArrayList<listModal>) {
        list = get
        notifyDataSetChanged()
    }

    fun total(amount: Int) {
        var total = 0

        total += amount


    }
}