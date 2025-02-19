package com.ktor.bff.utilities

object Utils {
    fun readResourceFile(fileName: String): String? {
        return object {}.javaClass.classLoader.getResourceAsStream(fileName)?.bufferedReader().use { it?.readText() }
    }
}