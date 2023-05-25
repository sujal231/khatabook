package com.example.khatabook

class listModal {
    var id: Int = 0
    var amount: Int = 0
    lateinit var title: String
    lateinit var note: String
    var isExpense: Int = 0


    constructor(id: Int, amount: Int, title: String, note: String, isExpense: Int) {
        this.id = id
        this.amount = amount
        this.title = title
        this.note = note
        this.isExpense = isExpense
    }
}