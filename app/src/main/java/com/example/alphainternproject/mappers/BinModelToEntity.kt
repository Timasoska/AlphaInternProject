package com.example.alphainternproject.mappers

import com.example.alphainternproject.data.local.BinEntity
import com.example.alphainternproject.domain.model.BinModel

fun BinModel.toEntity(): BinEntity {
    return BinEntity(
        bankCity = bank.city ?: "",
        bankName = bank.name ?: "",
        bankPhone = bank.phone ?: "",
        bankUrl = bank.url ?: "",
        brand = brand ?: "",
        countryAlpha2 = country.alpha2 ?: "",
        countryCurrency = country.currency ?: "",
        countryEmoji = country.emoji ?: "",
        countryLatitude = country.latitude ?: 0,
        countryLongitude = country.longitude ?: 0,
        countryName = country.name ?: "",
        countryNumeric = country.numeric ?: "",
        numberLength = number?.length ?: 0,
        numberLuhn = number?.luhn ?: false,
        prepaid = prepaid,
        scheme = scheme ?: "",
        type = type ?: ""
    )
}
