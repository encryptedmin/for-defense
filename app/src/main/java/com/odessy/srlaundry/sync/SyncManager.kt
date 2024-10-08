package com.odessy.srlaundry.sync

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.odessy.srlaundry.database.AppDatabase
import kotlinx.coroutines.CoroutineScope

class SyncManager(private val context: Context, private val scope: CoroutineScope) : BaseSyncManager() {

    private val firestoreDb = FirebaseFirestore.getInstance()
    private val db = AppDatabase.getDatabase(context, scope)

    // Function to sync all data with Firestore
    suspend fun syncAllData() {
        syncJobOrders()
        syncLaundrySales()
        syncTransactions()
        syncSmsMessages()  // Sync SMS messages
        syncStoreItems()    // Sync Store Items
    }

    private suspend fun syncJobOrders() {
        val jobOrders = db.jobOrderDao().getAllJobOrdersForSync()
        syncDataToFirestore(firestoreDb.collection("job_orders"), jobOrders) { jobOrder ->
            jobOrder.id.toString()
        }
    }

    private suspend fun syncLaundrySales() {
        val laundrySales = db.laundrySalesDao().getAllLaundrySales()
        syncDataToFirestore(firestoreDb.collection("laundry_sales"), laundrySales) { sale ->
            sale.id.toString()
        }
    }

    private suspend fun syncTransactions() {
        val transactions = db.transactionDao().getAllTransactions()
        syncDataToFirestore(firestoreDb.collection("transactions"), transactions) { transaction ->
            transaction.id.toString()
        }
    }


    private suspend fun syncSmsMessages() {
        val smsMessages = db.smsMessageDao().getAllSmsMessages()
        syncDataToFirestore(firestoreDb.collection("sms_messages"), smsMessages) { message ->
            message.id.toString()
        }
    }


    private suspend fun syncStoreItems() {
        val storeItems = db.storeItemDao().getAllStoreItemsSync()
        syncDataToFirestore(firestoreDb.collection("store_items"), storeItems) { item ->
            item.productName // Use productName as the document ID in Firestore
        }
    }
}