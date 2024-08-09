package com.example.testchat.retrofit

import com.example.testchat.retrofit.model.ResponseError
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object ErrorParser {
    fun parseError(errorBody: String?): String {
        return errorBody?.let {
            try {
                val json = Json { ignoreUnknownKeys = true }
                val element = json.parseToJsonElement(it)

                val detailElement = element.jsonObject["detail"]
                return when (val detail = detailElement) {
                    is JsonArray -> detail.joinToString(", ") { it.jsonPrimitive.content }
                    is JsonPrimitive -> detail.content
                    else -> "Unknown error"
                }
            } catch (e: SerializationException) {

                it
            } catch (e: Exception) {
                println("Error parsing error response: ${e.message}")
                "Unknown error"
            }
        } ?: "Unknown error"
    }

}