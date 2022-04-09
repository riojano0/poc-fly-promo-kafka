package io.github.riojano0.flypromofly2EU.service

import io.github.riojano0.flypromokafka.core.model.Country
import io.github.riojano0.flypromokafka.core.model.FlyPromo
import io.github.riojano0.flypromofly2EU.model.CountryProvider
import io.github.riojano0.flypromofly2EU.model.Fly2EUFlyPromo
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.Random


@Service
@Slf4j
class FlyPromoService
@Autowired constructor(
        @Qualifier("kafkaFlyPromoTemplate") val kafkaTemplate: KafkaTemplate<String, FlyPromo>) {

    private final val log: Logger = LoggerFactory.getLogger(FlyPromoService::class.java)
    private final val random: Random = Random()
    private final val promotionalPrices = mutableMapOf(
            0 to listOf<BigDecimal>(BigDecimal.valueOf(1000), BigDecimal.valueOf(750)),
            1 to listOf<BigDecimal>(BigDecimal.valueOf(1000), BigDecimal.valueOf(500)),
            2 to listOf<BigDecimal>(BigDecimal.valueOf(1000), BigDecimal.valueOf(450)),
            3 to listOf<BigDecimal>(BigDecimal.valueOf(1000), BigDecimal.valueOf(950))
    )

    fun generateFlyPromo() : FlyPromo {
        val fly2EuFlyPromo = Fly2EUFlyPromo()
        val values = CountryProvider.values()
        val toCountry = values[random.nextInt(values.size)]
        fly2EuFlyPromo.toCountry = toCountry

        val countries = arrayListOf<Country>()

        do {
            for (i in 0..2) {
                val country = values[random.nextInt(values.size)]
                if (country != toCountry) {
                    countries.add(country)
                }
            }
        } while (countries.isEmpty())
        fly2EuFlyPromo.fromCountries = countries;

        val bigDecimals = promotionalPrices[random.nextInt(promotionalPrices.size)]

        fly2EuFlyPromo.originalPrice = bigDecimals?.get(0)
        fly2EuFlyPromo.promotionalPrice = bigDecimals?.get(1)
        fly2EuFlyPromo.stock = 10
        val now = LocalDateTime.now()
        fly2EuFlyPromo.startDate = now
        fly2EuFlyPromo.endDate = now.plusDays(1)

        val listenableFuture = kafkaTemplate.send("fly-promo", fly2EuFlyPromo)
        listenableFuture.addCallback(
                { log.info("Complete Fly Promo") },
                { result -> log.error("Unable to send promo", result.cause) }
        )

        return fly2EuFlyPromo
    }

}