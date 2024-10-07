package com.odessy.srlaundry.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.odessy.srlaundry.entities.Transaction

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE timestamp BETWEEN :fromDate AND :toDate")
    suspend fun getTransactionsBetweenDates(fromDate: Long, toDate: Long): List<Transaction>
}