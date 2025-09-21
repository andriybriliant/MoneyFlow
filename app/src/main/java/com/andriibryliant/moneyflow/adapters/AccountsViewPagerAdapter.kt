package com.andriibryliant.moneyflow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andriibryliant.moneyflow.databinding.ItemAccountBinding
import com.andriibryliant.moneyflow.databinding.ItemAddAccountBinding
import com.andriibryliant.moneyflow.objects.Account
import com.andriibryliant.moneyflow.objects.AccountType
import java.text.DecimalFormat

class AccountsViewPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = emptyList<Account>()

    companion object{
        private const val TYPE_NORMAL = 0
        private const val TYPE_ADD_ACCOUNT = 1
    }

    fun setAccounts(list: List<Account>){
        items = list
        notifyDataSetChanged()
    }

    class NormalViewHolder(val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(account: Account){
            binding.nameText.text = account.name

            binding.amountText.text = when(account.type){
                AccountType.CRYPTO -> DecimalFormat("0.00000000").format(account.amount)
                else -> DecimalFormat("0.00").format(account.amount)
            }
            binding.currencyText.text = account.currency
        }
    }
    class AddAccountViewHolder(val binding: ItemAddAccountBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return if(position == items.size) TYPE_ADD_ACCOUNT else TYPE_NORMAL
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if(viewType == TYPE_ADD_ACCOUNT){
            AddAccountViewHolder(ItemAddAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{
            NormalViewHolder(ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if(holder is NormalViewHolder){
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

}