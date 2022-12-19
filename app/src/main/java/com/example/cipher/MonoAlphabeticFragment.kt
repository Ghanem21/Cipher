package com.example.cipher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.cipher.databinding.FragmentCaesarBinding
import com.example.cipher.databinding.FragmentMonoAlphabeticBinding


class MonoAlphabeticFragment : Fragment() {
    private lateinit var binding: FragmentMonoAlphabeticBinding
    private val viewModel: MonoAlphabeticViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mono_alphabetic, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}