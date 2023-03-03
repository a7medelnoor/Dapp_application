package com.a7medelnoor.Dapp_application.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.a7medelnoor.Dapp_application.R


class MainActivity : AppCompatActivity() {
    lateinit var buttonConnectWallet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonConnectWallet = findViewById(R.id.connect_metamsk)
        // connect metamask
        buttonConnectWallet.setOnClickListener {
            connectWalletMetaMask()
        }
        // connect wallet
    }

    fun connectWalletMetaMask() {

    }
}