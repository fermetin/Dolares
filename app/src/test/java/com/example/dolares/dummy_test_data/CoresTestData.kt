package com.example.dolares.dummy_test_data

import com.example.dolares.data.local.model.Core
import com.example.dolares.data.local.model.launch.Launch
import com.google.gson.Gson

fun createTestCoreDUMMY1(): Core {
    val jsonString = """{
    "block": 5,
    "reuse_count": 3,
    "rtls_attempts": 1,
    "rtls_landings": 1,
    "asds_attempts": 3,
    "asds_landings": 3,
    "last_update": "Landed on OCISLY as of Jan 29, 2020. ",
    "launches": [
        "5eb87d2bffd86e000604b375",
        "5eb87d31ffd86e000604b379",
        "5eb87d3fffd86e000604b382",
        "5eb87d44ffd86e000604b386"
    ],
    "serial": "B1051",
    "status": "active",
    "id": "5e9e28a6f35918c0803b265c"
}"""
    val gson = Gson()
    val testCore = gson.fromJson<Core>(jsonString, Core::class.java)

    return testCore
}
fun createTestCoreDUMMY2(): Core {
    val jsonString = """{
    "block": 5,
    "reuse_count": 3,
    "rtls_attempts": 1,
    "rtls_landings": 1,
    "asds_attempts": 3,
    "asds_landings": 3,
    "last_update": "Landed on OCISLY as of Jan 29, 2020. ",
    "launches": [
        "5eb87d2bffd86e000604b375",
        "5eb87d31ffd86e000604b379",
        "5eb87d3fffd86e000604b382",
        "5eb87d44ffd86e000604b386"
    ],
    "serial": "B1051",
    "status": "active",
    "id": "5e9e28aasdf6f35918c0803b265c"
}"""
    val gson = Gson()
    val testCore = gson.fromJson<Core>(jsonString, Core::class.java)

    return testCore
}
