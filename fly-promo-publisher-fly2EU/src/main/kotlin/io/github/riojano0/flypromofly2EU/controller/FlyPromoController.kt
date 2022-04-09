package io.github.riojano0.flypromofly2EU.controller

import io.github.riojano0.flypromokafka.model.FlyPromo
import io.github.riojano0.flypromofly2EU.service.FlyPromoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class FlyPromoController @Autowired constructor(val flyPromoService: FlyPromoService) {

    @GetMapping
    fun publishPromoDummy(): FlyPromo {
        return flyPromoService.generateFlyPromo()
    }

}