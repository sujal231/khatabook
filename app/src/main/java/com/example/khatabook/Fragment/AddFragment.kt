package com.example.khatabook.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.khatabook.DbHelper
import com.example.khatabook.TransactionlistAdapter
import com.example.khatabook.databinding.FragmentAddBinding
import com.example.khatabook.listModal

class AddFragment : Fragment() {


    lateinit var Binging: FragmentAddBinding
    lateinit var dbHelper: DbHelper
    lateinit var adapter: TransactionlistAdapter
    var isExpense = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Binging = FragmentAddBinding.inflate(layoutInflater)

        dbHelper = DbHelper(context)

        Binging.idisexpenseincome.setOnClickListener {
            isExpense = 0
        }
        Binging.idisexpenseExpense.setOnClickListener {
            isExpense = 1
        }
        Binging.btnsave.setOnClickListener {
            var amount = Binging.edtamount.text.toString().toInt()
            var title = Binging.edttitle.text.toString()
            var note = Binging.edtnote.text.toString()

            var data = listModal(0, amount, title, note, isExpense)

            dbHelper.add(data)
//            adapter.update(dbHelper.get())
            clearEditText()

//            var intent = Intent(context,AddFragment()::class.java)
//            startActivity(intent)
        }


//        var list = dbHelper.get()
//        adapter = TransactionlistAdapter(list)
//        Binging.rcv.layoutManager = LinearLayoutManager(context)
//        Binging.rcv.adapter = adapter

        return Binging.root

    }

    private fun clearEditText() {
        Binging.edtamount.setText("")
        Binging.edtnote.setText("")
        Binging.edttitle.setText("")
    }


}