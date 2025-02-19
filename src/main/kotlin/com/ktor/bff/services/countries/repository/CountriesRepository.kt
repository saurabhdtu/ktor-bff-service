package com.ktor.bff.services.countries.repository

import com.ktor.bff.commons.ServerConstants
import com.zinc.money.bff.utilities.Utils

object CountriesRepository {

    fun getAllCountries(): String {
        val countries = Utils.readResourceFile(com.ktor.bff.commons.ServerConstants.countriesConfig)
        return countries ?: "[]"
    }
}