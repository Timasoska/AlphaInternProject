package com.example.alphainternproject.domain.model

import com.example.alphainternproject.data.local.BinEntity

data class BinModel(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)

// Преобразование из BinModel в BinEntity
fun BinModel.toEntity(): BinEntity {
    return BinEntity(
        bankCity = bank.city,
        bankName = bank.name,
        bankPhone = bank.phone,
        bankUrl = bank.url,
        brand = brand,
        countryAlpha2 = country.alpha2,
        countryCurrency = country.currency,
        countryEmoji = country.emoji,
        countryLatitude = country.latitude,
        countryLongitude = country.longitude,
        countryName = country.name,
        countryNumeric = country.numeric,
        numberLength = number.length,
        numberLuhn = number.luhn,
        prepaid = prepaid,
        scheme = scheme,
        type = type
    )
}