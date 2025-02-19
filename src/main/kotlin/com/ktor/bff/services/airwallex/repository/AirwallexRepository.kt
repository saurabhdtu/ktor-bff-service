package com.ktor.bff.services.airwallex.repository

import ConnectedAccount
import com.zinc.money.bff.plugins.ApiClient
import com.zinc.money.bff.plugins.ApiException
import io.ktor.http.*

object AirwallexRepository {

    suspend fun getConnectedAccount(phone: String?): ConnectedAccount {
        if (phone.isNullOrBlank())
            throw ApiException.BadRequestException("Phone number not specified")
        val response = ApiClient.makeRequest<ConnectedAccount>(
            ConnectedAccountEndpoints.getConnectedAccount, HttpMethod.Get, headers = null,
            pathParams = mapOf("phone" to "91-9650076366"),
            null,
        )
        return response
    }
}