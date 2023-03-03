package com.a7medelnoor.Dapp_application

import android.app.Application
import android.util.Log
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.android.relay.ConnectionType

class DappApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        val projectId = "0f86a9e3f20df7f4675837aa9750e70c" // Get Project ID at https://cloud.walletconnect.com/
        val relayUrl = "relay.walletconnect.com"
//        val serverUrl = "wss://$relayUrl?projectId=$projectId"
//        val connectionType = ConnectionType.AUTOMATIC
//
//        val appMetaData = Core.Model.AppMetaData(
//            name = "Wallet Name",
//            description = "Wallet Description",
//            url = "Wallet Url",
//            icons = /*list of icon url strings*/,
//            redirect = "kotlin-wallet-wc:/request" // Custom Redirect URI
//        )
//
//        CoreClient.initialize(relayServerUrl = serverUrl, connectionType = connectionType,
//            application = this, metaData = appMetaData)
////            onError = (Sign.Model.Error)
//        val init = Sign.Params.Init(core = CoreClient)
//        SignClient.initialize(init) { error ->
//            // Error will be thrown if there's an isssue during initalization
//        }

        val serverUri = "wss://$relayUrl?projectId=0f86a9e3f20df7f4675837aa9750e70c"
        CoreClient.initialize(
            relayServerUrl = serverUri,
            connectionType = ConnectionType.AUTOMATIC,
            application = this,
            metaData = Core.Model.AppMetaData(
                name = "Dapp VAI test",
                description = "Dapp test description ",
                url = "example.dapp",
                icons = listOf("https://gblobscdn.gitbook.com/spaces%2F-LJJeCjcLrr53DcT1Ml7%2Favatar.png?alt=media"),
                redirect = "kotlin-dapp-wc:/request"
            )
        ) { error -> Log.e("Error", error.throwable.stackTraceToString()) }


    }

}
