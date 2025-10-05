package com.andriibryliant.moneyflow.objects


data class Jar(
    val name: String,
    val goal: Double,
    val amountAchieved: Double,
    val type: JarType,
    val frequency: String,
    val recurringAmount: Double,
    val currency: String
)

enum class JarType{
    DEFAULT,
    RECURRING
}