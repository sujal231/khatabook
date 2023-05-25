package com.example.khatabook

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

var table_name = "list"

class DbHelper(context: Context?) : SQLiteOpenHelper(context, "myData.db", null, 1) {


    override fun onCreate(p0: SQLiteDatabase?) {
        var SQL =
            "CREATE TABLE $table_name(ID INTEGER PRIMARY KEY AUTOINCREMENT,Amount INTEGER,title TEXT,note TEXT,isExpanse INTEGER)"
        p0?.execSQL(SQL)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun add(data: listModal?) {
        var db = writableDatabase

        var values = ContentValues().apply {

            put("Amount", data?.amount)
            put("title", data?.title)
            put("note", data?.note)
            put("isExpanse", data?.isExpense)


        }
        var iss = db.insert(table_name, null, values)

        if (iss.toInt() == -1) {
            Log.e(TAG, "addstd: ________________________________yes")
        } else {
            Log.e(TAG, "addstd: ________________________________no................")
        }
    }

    fun get(): ArrayList<listModal> {
        var Transactionlist = ArrayList<listModal>()
        var db = readableDatabase
        var SQl = "SELECT * FROM $table_name"
        var cursor: Cursor = db.rawQuery(SQl, null)
        cursor.moveToFirst()

        for (i in 0..cursor.count - 1) {
            var id = cursor.getInt(0)
            var amount = cursor.getInt(1)
            var title = cursor.getString(2)
            var note = cursor.getString(3)
            var isExpanse = cursor.getInt(4)

            var model = listModal(id, amount, title, note,isExpanse)
            Transactionlist.add(model)
            cursor.moveToNext()
        }

        return Transactionlist
    }
}





