package com.example.alphainternproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "bin_table")
data class BinEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val bankCity: String,
    val bankName: String,
    val bankPhone: String,
    val bankUrl: String,
    val countryAlpha2: String,
    val countryCurrency: String,
    val countryEmoji: String,
    val countryLatitude: Int,
    val countryLongitude: Int,
    val countryName: String,
    val countryNumeric: String,
    val numberLength: Int,
    val numberLuhn: Boolean,
    val brand: String,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)



