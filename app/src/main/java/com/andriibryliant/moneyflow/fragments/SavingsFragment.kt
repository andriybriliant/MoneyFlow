package com.andriibryliant.moneyflow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.adapters.JarsViewPagerAdapter
import com.andriibryliant.moneyflow.databinding.FragmentSavingsBinding
import com.andriibryliant.moneyflow.viewmodels.JarsViewModel

class SavingsFragment : Fragment(R.layout.fragment_savings) {
    private lateinit var binding: FragmentSavingsBinding
    private val jarsViewModel: JarsViewModel by viewModels()
    private val jarsViewPagerAdapter = JarsViewPagerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavingsBinding.bind(view)

        binding.jarsViewPager.adapter = jarsViewPagerAdapter

        jarsViewModel.jarsList.observe(viewLifecycleOwner){ items ->
            jarsViewPagerAdapter.setJars(items)
        }

        jarsViewModel.fetchJars()
    }
}