package com.example.alphainternproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alphainternproject.domain.model.Bank
import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.model.Country
import com.example.alphainternproject.domain.model.Number

@Entity(tableName = "bin_table")
data class BinEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    // Поля из Bank
    val bankCity: String,
    val bankName: String,
    val bankPhone: String,
    val bankUrl: String,
    // Поля из Country
    val countryAlpha2: String,
    val countryCurrency: String,
    val countryEmoji: String,
    val countryLatitude: Int,
    val countryLongitude: Int,
    val countryName: String,
    val countryNumeric: String,
    // Поля из Number
    val numberLength: Int,
    val numberLuhn: Boolean,
    // Остальные поля BinEntity
    val brand: String,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)

// Преобразование из BinEntity в BinModel
fun BinEntity.toDomainModel(): BinModel {
    return BinModel(
        bank = Bank(
            city = bankCity,
            name = bankName,
            phone = bankPhone,
            url = bankUrl
        ),
        brand = brand,
        country = Country(
            alpha2 = countryAlpha2,
            currency = countryCurrency,
            emoji = countryEmoji,
            latitude = countryLatitude,
            longitude = countryLongitude,
            name = countryName,
            numeric = countryNumeric
        ),
        number = Number(
            length = numberLength,
            luhn = numberLuhn
        ),
        prepaid = prepaid,
        scheme = scheme,
        type = type
    )
}