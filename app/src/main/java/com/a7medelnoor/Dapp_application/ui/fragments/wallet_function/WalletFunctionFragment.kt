package com.a7medelnoor.Dapp_application.ui.fragments.wallet_function

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.a7medelnoor.Dapp_application.databinding.FragmentWalletFunctionBinding
import com.a7medelnoor.Dapp_application.ui.viewmodel.ConnectWalletViewModel
import com.a7medelnoor.Dapp_application.ui.viewmodel.NfcViewModel
import timber.log.Timber


class WalletFunctionFragment : Fragment(), NfcAdapter.CreateNdefMessageCallback {

    private var _binding: FragmentWalletFunctionBinding? = null
    private var nfcAdapter: NfcAdapter? = null

    override fun onStart() {
        super.onStart()
        if (activity != null && requireActivity().intent.hasExtra("")) {
        }
    }

    private val binding get() = _binding!!
    private val viewmodel: ConnectWalletViewModel by activityViewModels()
    private val nfcViewmodel: NfcViewModel by activityViewModels()
    private val TAG = "WalletFunctionFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWalletFunctionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nfcAdapter = NfcAdapter.getDefaultAdapter(requireContext())
        if (nfcAdapter == null) {
            Toast.makeText(requireActivity(), "NFC is not enabled", Toast.LENGTH_SHORT).show()
            return
        }
        nfcAdapter?.setNdefPushMessageCallback(this, requireActivity())


//        Coroutines.main(this) { scope ->
//            scope.launch(block = {
//                nfcViewmodel.observeNFCStatus()?.collectLatest(action = { status ->
//                    Log.d(TAG, "observeNFCStatus $status")
//                    if (status == NFCStatus.NoOperation) context?.let {
//                        NfcManager.disableReaderMode(
//                            it,
//                            requireActivity()
//                        )
//                    }
//                    else if (status == NFCStatus.Tap) context?.let {
//                        NfcManager.enableReaderMode(
//                            it,
//                            requireActivity(),
//                            this@WalletFunctionFragment,
//                            nfcViewmodel.getNFCFlags(),
//                            nfcViewmodel.getExtras()
//                        )
//                    }
//                })
//            })
//            scope.launch(block = {
//                nfcViewmodel.observeToast()?.collectLatest(action = { message ->
//                    Log.d(TAG, "observeToast $message")
//                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//                })
//            })
//            scope.launch(block = {
//                nfcViewmodel.observeTag()?.collectLatest(action = { tag ->
//                    Log.d(TAG, "observeTag $tag")
////                    binding.shareAddress?.setText(tag)
//                })
//            })
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getAccounts().observe(viewLifecycleOwner) {
            for (element in it) {
                binding.walletBalance.text = element.toString()
                Timber.tag(TAG).d("Account:  $it")
            }
            binding.shareAddress.setOnClickListener {
                // share address to NFC device
                nfcViewmodel.onCheckNFC(it.isClickable)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onTagDiscovered(tag: Tag?) {
//         nfcViewmodel.readTag(tag)
//    }

    override fun onResume() {
        super.onResume()
        val intent: Intent? = null
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent?.action) {
            processIntent(intent)
        }
    }

    private fun processIntent(intent: Intent) {
        // only one message sent during the beam
        intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMsgs ->
            (rawMsgs[0] as NdefMessage).apply {
                // record 0 contains the MIME type, record 1 is the AAR, if present
                binding.shareAddress.text = String(records[0].payload)
            }
        }
    }


    override fun createNdefMessage(event: NfcEvent?): NdefMessage {
        val text = "Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis()
        return NdefMessage(
            arrayOf(
                NdefRecord.createMime(
                    "application/vnd.com.example.android.beam",
                    text.toByteArray()
                )
            )
        )
    }

}