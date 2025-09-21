package com.andriibryliant.moneyflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriibryliant.moneyflow.objects.Account
import com.andriibryliant.moneyflow.objects.AccountType

class AccountsViewModel : ViewModel() {
    private var _accounts = MutableLiveData<List<Account>>()
    val accountList: LiveData<List<Account>> = _accounts

    fun fetchAccounts(){
        loadDummyData()
    }

    fun loadDummyData(){
        _accounts.value = listOf(
            Account("account1", "Cash", 2000.0, AccountType.CASH,"zł"),
            Account("account1", "Millennium Bank", 343.0, AccountType.BANK,"zł"),
            Account("account1", "Savings", 4500.0, AccountType.SAVINGS, "zł"),
            Account("account1", "Crypto", 0.0000043, AccountType.CRYPTO, "BTC")
        )
    }
}