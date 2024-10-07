package com.odessy.srlaundry.others

data class LaundrySalesData(
    val laundryType: String,
    val totalIncome: Double,
    val totalLoads: Int,
    val totalDetergentAddons: Int,
    val totalFabricConditionerAddons: Int,
    val totalBleachAddons: Int
)