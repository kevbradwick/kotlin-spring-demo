package com.kodefoundry.apidemo

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.string
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.filter
import kotlin.collections.map
import kotlin.text.contains
import kotlin.text.startsWith

/**
 * Extends the JsonObject to provide a method for converting to VTS data class
 *
 * @return VehicleTestingStation
 */
fun JsonObject.toVts() : VehicleTestingStation {
    return VehicleTestingStation(
            this.string("siteNumber")!!,
            this.string("tradingName")!!,
            this.string("address")!!,
            this.string("telephone")!!
    )
}

@RestController
@RequestMapping(path = arrayOf("/vts"))
class VTSController {

    @RequestMapping("/site-number/{id}")
    fun bySiteNumber(@PathVariable id: String): VehicleTestingStation {

        val obj = getStationsBy("vts")

        if (!obj.containsKey(id)) {
            throw VTSNotFoundException()
        }

        return (obj[id] as JsonObject).toVts()
    }

    @RequestMapping("/name/{name}")
    fun byTradingName(@PathVariable name: String): List<VehicleTestingStation> {

        val obj = getStationsBy("name")
        val stations = obj.keys.filter { it.contains(name, true) }.map { (obj[it] as JsonObject).toVts() }

        if (stations.size == 0) {
            throw VTSNotFoundException()
        }

        return stations
    }

    @RequestMapping("/post-code/{postCode}")
    fun byPostCode(@PathVariable postCode: String): List<VehicleTestingStation> {

        val obj = getStationsBy("postcode")
        val stations = obj.keys.filter { it.startsWith(postCode, true) }.map { (obj[it] as JsonObject).toVts() }

        if (stations.size == 0) {
            throw VTSNotFoundException()
        }

        return stations
    }

    /**
     * Read the json files and return the JsonObject
     *
     * @param type the json file who's keys are this type e.g. postcode
     *
     * @return JsonObject
     */
    fun getStationsBy(type: String): JsonObject {
        val fileName = "/vts2015-by-$type.json"
        val inputStream = Parser::class.java.getResourceAsStream(fileName)
        return Parser().parse(inputStream) as JsonObject
    }
}
