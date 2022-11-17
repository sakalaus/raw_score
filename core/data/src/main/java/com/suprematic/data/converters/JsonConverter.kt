package com.suprematic.data.converters

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonConverter @Inject constructor(val json: Json) {

    inline fun <reified T> toJson(clazz: T): String {
        return this.json.encodeToString(clazz)
    }

    inline fun <reified T> fromJson(json: String): T? {
        return this.json.decodeFromStringOrNull(json)
    }
}

inline fun <reified T> Json.decodeFromStringOrNull(json: String): T? {
    return try {
        decodeFromString(json)
    } catch (ignore: Throwable) {
        null
    }
}

