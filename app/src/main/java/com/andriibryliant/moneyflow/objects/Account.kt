package com.andriibryliant.moneyflow.objects

data class Account(
    val id: String,
    val name: String,
    val amount: Double,
    val type: AccountType,
    val currency: String,
    val iconRes: Int?
)

enum class AccountType{
    CASH,
    BANK,
    SAVINGS,
    CRYPTO
}