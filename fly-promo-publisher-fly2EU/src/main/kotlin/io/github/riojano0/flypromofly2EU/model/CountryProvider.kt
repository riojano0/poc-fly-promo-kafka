package io.github.riojano0.flypromofly2EU.model

import io.github.riojano0.flypromokafka.core.model.Country

enum class CountryProvider(private val country: Country) : Country {

    SPAIN(Country { "SPA" }),
    PORTUGAL(Country { "POR" }),
    ITALY(Country { "ITA" }),
    GERMANY(Country { "GER" }),
    FRANCE(Country { "FRA" });

    override fun getISO3(): String {
        return this.country.isO3
    }
}