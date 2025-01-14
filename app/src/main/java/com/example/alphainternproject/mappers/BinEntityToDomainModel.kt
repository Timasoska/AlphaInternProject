package com.example.alphainternproject.mappers

import com.example.alphainternproject.data.local.BinEntity
import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.model.Bank
import com.example.alphainternproject.domain.model.Country
import com.example.alphainternproject.domain.model.Number

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
