package com.example.alphainternproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "bin_table")
data class BinEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val bankCity: String = "",
    val bankName: String = "",
    val bankPhone: String = "",
    val bankUrl: String = "",
    val countryAlpha2: String = "",
    val countryCurrency: String = "",
    val countryEmoji: String = "",
    val countryLatitude: Int = 0,
    val countryLongitude: Int = 0,
    val countryName: String = "",
    val countryNumeric: String = "",
    val numberLength: Int = 0,
    val numberLuhn: Boolean = false,
    val brand: String = "",
    val prepaid: Boolean = false,
    val scheme: String = "",
    val type: String = ""
)



