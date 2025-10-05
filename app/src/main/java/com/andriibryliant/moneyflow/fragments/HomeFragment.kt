package com.andriibryliant.moneyflow.fragments

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.adapters.AccountsViewPagerAdapter
import com.andriibryliant.moneyflow.adapters.TransactionsRecyclerViewAdapter

import com.andriibryliant.moneyflow.databinding.FragmentHomeBinding
import com.andriibryliant.moneyflow.objects.TransactionListItem
import com.andriibryliant.moneyflow.utils.Animations
import com.andriibryliant.moneyflow.viewmodels.AccountsViewModel
import com.andriibryliant.moneyflow.viewmodels.CategoriesViewModel
import com.andriibryliant.moneyflow.viewmodels.MenuViewModel
import com.andriibryliant.moneyflow.viewmodels.TransactionsViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private var lastSelected: TransactionListItem? = null
    private val accountsViewPagerAdapter = AccountsViewPagerAdapter()
    private val transactionsRecyclerViewAdapter = TransactionsRecyclerViewAdapter()
    private val menuViewModel: MenuViewModel<TransactionListItem> by viewModels{ MenuViewModel.MenuViewModelFactory(
        TransactionListItem.ALL) }
    private val accountsViewModel: AccountsViewModel by viewModels()
    private val transactionsViewModel: TransactionsViewModel by viewModels()
    private val categoriesViewModel: CategoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.transactionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.transactionsRecyclerView.adapter = transactionsRecyclerViewAdapter
        binding.accountsViewPager.adapter = accountsViewPagerAdapter

        binding.main.layoutTransition = LayoutTransition()

        binding.allText.setOnClickListener {
            menuViewModel.selectMenuItem(TransactionListItem.ALL)
            transactionsViewModel.selectFilter(TransactionListItem.ALL)
        }
        binding.incomeText.setOnClickListener {
            menuViewModel.selectMenuItem(TransactionListItem.INCOME)
            transactionsViewModel.selectFilter(TransactionListItem.INCOME)
        }
        binding.expenseText.setOnClickListener {
            menuViewModel.selectMenuItem(TransactionListItem.EXPENSE)
            transactionsViewModel.selectFilter(TransactionListItem.EXPENSE)
        }

        menuViewModel.selectedMenuItem.observe(viewLifecycleOwner){ item ->
            updateMenuUI(item)
        }

        accountsViewModel.accountList.observe(viewLifecycleOwner){ it ->
            accountsViewPagerAdapter.setAccounts(it)
        }

        transactionsViewModel.transactionList.observe(viewLifecycleOwner){ it ->
            transactionsRecyclerViewAdapter.setTransactions(it)
        }

        categoriesViewModel.categoriesList.observe(viewLifecycleOwner){it ->
            transactionsRecyclerViewAdapter.setCategories(it)
        }

        binding.accountsViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position == accountsViewModel.accountList.value?.size){
                    showAddAccountMessage()
                }
                else{
                    hideAddAccountMessage()
                }
            }
        })
    }

    private fun updateMenuUI(item: TransactionListItem){
        if(item != lastSelected){
            animateMenuItem(item)
            lastSelected = item
        }else{
            setMenuSelectedUI(item)
        }
    }

    private fun animateMenuItem(item: TransactionListItem){
            val selectedView = when(item){
                TransactionListItem.ALL -> binding.allSelected
                TransactionListItem.INCOME -> binding.incomeSelected
                TransactionListItem.EXPENSE -> binding.expenseSelected
            }

            val showAnimation = Animations.buttonShowAnimation(400)

            listOf(binding.allSelected, binding.incomeSelected, binding.expenseSelected).forEach {
                it.clearAnimation()
                it.visibility = View.INVISIBLE
            }

            selectedView.visibility = View.VISIBLE
            selectedView.startAnimation(showAnimation)
    }

    private fun setMenuSelectedUI(item: TransactionListItem){
        val selectedView = when(item){
            TransactionListItem.ALL -> binding.allSelected
            TransactionListItem.INCOME -> binding.incomeSelected
            TransactionListItem.EXPENSE -> binding.expenseSelected
        }

        listOf(binding.allSelected, binding.incomeSelected, binding.expenseSelected).forEach {
            it.clearAnimation()
            it.visibility = View.INVISIBLE
        }

        selectedView.visibility = View.VISIBLE
    }

    private fun showAddAccountMessage(){
        binding.noAccountMessage.visibility = View.VISIBLE
        binding.transactionTypeMenu.visibility = View.GONE
        binding.transactionsRecyclerView.visibility = View.GONE
    }

    private fun hideAddAccountMessage(){
        binding.noAccountMessage.visibility = View.GONE
        binding.transactionTypeMenu.visibility = View.VISIBLE
        binding.transactionsRecyclerView.visibility = View.VISIBLE
    }
}