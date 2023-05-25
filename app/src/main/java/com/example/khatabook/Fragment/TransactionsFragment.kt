package com.example.khatabook.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.DbHelper
import com.example.khatabook.TransactionlistAdapter
import com.example.khatabook.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment() {

    lateinit var Binding: FragmentTransactionsBinding
    lateinit var adapter: TransactionlistAdapter
    lateinit var dbHelper: DbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentTransactionsBinding.inflate(layoutInflater)

        dbHelper = DbHelper(context)

        var list = dbHelper.get()
//        adapter.update(list)


        adapter = TransactionlistAdapter(list)

        Binding.rcvadd.layoutManager = LinearLayoutManager(context)
        Binding.rcvadd.adapter = adapter



        return Binding.root
    }
}