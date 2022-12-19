package com.example.cipher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.cipher.databinding.FragmentCaesarBinding
import com.example.cipher.databinding.FragmentPlayFairBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class PlayFairFragment : Fragment() {
    private lateinit var binding: FragmentPlayFairBinding
    private val viewModel: PlayFairViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_play_fair, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dialog.observe(viewLifecycleOwner){
            if(it){
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Invalid input")
                    .setMessage("PlainText must be even number and no double letter consecutive")
                    .setPositiveButton("ok"){dialog,_->
                        dialog.dismiss()
                    }.show()
                viewModel.doneShowDialog()
            }
        }
    }
}