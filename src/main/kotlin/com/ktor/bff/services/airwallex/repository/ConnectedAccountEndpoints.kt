package com.ktor.bff.services.airwallex.repository

import com.ktor.bff.commons.ServerConstants

object ConnectedAccountEndpoints {
    private val apiVersion = "v3"
    val getConnectedAccount = "${com.ktor.bff.commons.ServerConstants.serverConfig.airwallexServiceUrl}${apiVersion}/connectedaccount/{phone}"
}