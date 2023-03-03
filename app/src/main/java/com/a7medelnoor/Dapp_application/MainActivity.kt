package com.a7medelnoor.Dapp_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var buttonConnectWallet : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonConnectWallet = findViewById(R.id.connect_metamsk)
       // connect metamask
        buttonConnectWallet.setOnClickListener{
          connectWalletMetaMask();
        }
        // connect wallet
    }
    fun connectWalletMetaMask(){

    }
}