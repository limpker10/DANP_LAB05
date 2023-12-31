package com.example.app04

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class ApiService {
    private val client = HttpClient()
    companion object {
        suspend fun callLambdaFunction(page: Int, size: Int): List<DataItem> {
            val client = HttpClient() {
                install(JsonFeature) {
                    serializer = GsonSerializer()
                }
            }

            return withContext(Dispatchers.IO) {
                try {
                    val response: String = client.get("https://mjrhtbnbtaofaeehiiykatfdki0hfmba.lambda-url.us-east-2.on.aws") {
                        url {
                            parameters.append("page", page.toString())
                            parameters.append("size", size.toString())
                        }
                    }

                    val gson = Gson()
                    val dataItems: List<DataItem> = gson.fromJson(response, object : TypeToken<List<DataItem>>() {}.type)
                    dataItems
                } catch (e: Exception) {
                    e.printStackTrace()
                    emptyList() // En caso de error, se devuelve una lista vacía
                }
            }
        }

        suspend fun postLambdaFunction(temperature: Double, unit: String, comentario:String): String {
            val client = HttpClient() {
                install(JsonFeature) {
                    serializer = GsonSerializer()
                }
            }

            return withContext(Dispatchers.IO) {
                try {
                    val response: String = client.get("https://mjrhtbnbtaofaeehiiykatfdki0hfmba.lambda-url.us-east-2.on.aws/add") {
                        url {
                            parameters.append("temperature", temperature.toString())
                            parameters.append("unit", unit.toString())
                        }
                    }
                    response
                } catch (e: Exception) {
                    e.printStackTrace()
                    "Error" // In case of error, return an empty string
                }
            }
        }

    }
}