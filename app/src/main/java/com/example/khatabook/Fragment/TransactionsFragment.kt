package com.example.khatabook.Fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.DbHelper
import com.example.khatabook.TransactionlistAdapter
import com.example.khatabook.databinding.DialogBinding
import com.example.khatabook.databinding.FragmentTransactionsBinding
import com.example.khatabook.databinding.InfoDialogBinding
import com.example.khatabook.listModal

class TransactionsFragment : Fragment() {

    lateinit var Binding: FragmentTransactionsBinding
    lateinit var adapter: TransactionlistAdapter
    lateinit var dbHelper: DbHelper
    var isExpense = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentTransactionsBinding.inflate(layoutInflater)

        dbHelper = DbHelper(context)

        var list = dbHelper.get()
//        adapter.update(list)

        total(list)

        adapter = TransactionlistAdapter({

            updatadialog(it)

        }, list, {
            delete(it)
        }, { info(it) })

//        adapter = TransactionlistAdapter(list)
        Binding.rcvadd.layoutManager = LinearLayoutManager(context)
        Binding.rcvadd.adapter = adapter




        return Binding.root
    }

    public fun info(it: listModal) {

        var dialog = Dialog(requireContext())
        var b = InfoDialogBinding.inflate(layoutInflater)




        if (it.isExpense == 0) {
            b.you2.setText(it.title)
            b.you.setText("You")
        } else {

            b.you2.setText("You")
            b.you.setText(it.title)
        }




        dialog.setContentView(b.root)
        dialog.show()

    }


    private fun delete(it: Int) {

        dbHelper.delete(it)
        adapter.update(dbHelper.get().reversed() as ArrayList<listModal>)
    }

    private fun updatadialog(listModal: listModal) {
        var dialog = Dialog(requireContext())
        var bind = DialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.edtamount.setText(listModal.amount.toString())
        bind.edtnote.setText(listModal.note.toString())
        bind.edttitle.setText(listModal.title.toString())

        bind.btnsave.setOnClickListener {


            var amount = bind.edtamount.text.toString().toInt()
            var title = bind.edttitle.text.toString()
            var note = bind.edtnote.text.toString()

            var modal = listModal(listModal.id, amount, title, note, listModal.isExpense)

            dbHelper.updateTrans(modal)
            dialog.dismiss()
            adapter.update(dbHelper.get())


//            var intent = Intent(context,AddFragment()::class.java)
//            startActivity(intent)
        }

        dialog.show()

    }


    fun total(list: ArrayList<listModal>) {

        var totalin = 0
        var totalex = 0
        for (trans in list) {


            if (trans.isExpense == 0) {
                totalin += trans.amount
            } else {
                totalex += trans.amount
            }
        }

        Binding.txtin.text = totalin.toString()
        Binding.txtex.text = totalex.toString()
        Binding.txttotel.text = (totalin - totalex).toString()
    }


}