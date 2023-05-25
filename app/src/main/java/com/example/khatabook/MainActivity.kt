package com.example.khatabook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.khatabook.Fragment.AddFragment
import com.example.khatabook.Fragment.StatsFragment
import com.example.khatabook.Fragment.TransactionsFragment
import com.example.khatabook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var Transactions = TransactionsFragment()
        var Stats = StatsFragment()
        var Add = AddFragment()

        setCurrentFragment(Transactions)

        binding.tab.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tabadd -> setCurrentFragment(Add)
                R.id.tabstats -> setCurrentFragment(Stats)
                R.id.tabtransaction -> setCurrentFragment(Transactions)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.rcv,fragment)
//            commit()

        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()

    }

}


