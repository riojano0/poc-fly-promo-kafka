package io.github.riojano0.flypromofly2EU.model

import io.github.riojano0.flypromokafka.model.FlyPromo
import java.util.*

class Fly2EUFlyPromo : FlyPromo("Fly-2-EU") {

    init {
        this.flyPromoId = this.provider + "-" + UUID.randomUUID()
    }

}