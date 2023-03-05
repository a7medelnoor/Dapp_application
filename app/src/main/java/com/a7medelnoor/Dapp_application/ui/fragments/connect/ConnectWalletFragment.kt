package com.a7medelnoor.Dapp_application.ui.fragments.connect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.a7medelnoor.Dapp_application.R
import com.a7medelnoor.Dapp_application.databinding.FragmentConnectWalletBinding
import com.a7medelnoor.Dapp_application.ui.viewmodel.ConnectWalletViewModel

class ConnectWalletFragment : Fragment() {

    private var _binding: FragmentConnectWalletBinding? = null

    private val binding get() = _binding!!
    private val viewmodel: ConnectWalletViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConnectWalletBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.isConnected().observe(viewLifecycleOwner) { isConnected ->
            binding.connectMetamsk.isEnabled = isConnected
            binding.connectMetamsk.setOnClickListener {
                findNavController().navigate(R.id.action_connectWalletFragment_to_walletFragment)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}