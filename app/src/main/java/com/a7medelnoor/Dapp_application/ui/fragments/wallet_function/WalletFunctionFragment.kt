package com.a7medelnoor.Dapp_application.ui.fragments.wallet_function

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.a7medelnoor.Dapp_application.databinding.FragmentWalletFunctionBinding
import com.a7medelnoor.Dapp_application.ui.viewmodel.ConnectWalletViewModel


class WalletFunctionFragment : Fragment() {

    private var _binding: FragmentWalletFunctionBinding? = null

    private val binding get() = _binding!!
    private val viewmodel: ConnectWalletViewModel by activityViewModels()
    private val TAG = "WalletFunctionFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWalletFunctionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getAccounts().observe(viewLifecycleOwner) {
            Log.d(TAG, "Account: $it")
            binding.walletBalance.text = it.toString()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}