package com.a7medelnoor.Dapp_application.ui.fragments.wallet

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.a7medelnoor.Dapp_application.R
import com.a7medelnoor.Dapp_application.databinding.FragmentConnectWalletBinding
import com.a7medelnoor.Dapp_application.databinding.FragmentConnectingWalletBinding
import com.a7medelnoor.Dapp_application.ui.viewmodel.ConnectWalletViewModel


class ConnectingWalletFragment : Fragment() {
    private var _binding: FragmentConnectingWalletBinding? = null

    private val binding get() = _binding!!
    private val viewmodel: ConnectWalletViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.signIn()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConnectingWalletBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.isWalletConnected().observe(viewLifecycleOwner){ isConnected ->
            binding.progressBar.visibility = View.VISIBLE
            findNavController().navigate(R.id.action_walletFragment_to_walletFunctionFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}