package com.andriibryliant.moneyflow.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andriibryliant.moneyflow.databinding.ItemJarBinding
import com.andriibryliant.moneyflow.objects.Jar
import com.andriibryliant.moneyflow.objects.JarType
import java.text.DecimalFormat

class JarsViewPagerAdapter : RecyclerView.Adapter<JarsViewPagerAdapter.ViewHolder>() {

    private var jarsList: List<Jar> = emptyList()

    fun setJars(items: List<Jar>){
        jarsList = items
    }

    class ViewHolder(val binding: ItemJarBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Jar){
            binding.jarNameText.text = item.name
            binding.amountText.text = DecimalFormat("0.00").format(item.amountAchieved)
            binding.currencyText.text = item.currency
            binding.goalText.text = DecimalFormat("0.00").format(item.goal) + item.currency

            if(item.type == JarType.RECURRING){
                binding.jarFrequencyText.visibility = View.VISIBLE
                val frequencyText: String = DecimalFormat("#.##").format(item.recurringAmount) + item.currency + " every " + item.frequency
                binding.jarFrequencyText.text = frequencyText
            }else{
                binding.jarFrequencyText.visibility = View.GONE
            }

            binding.jarProgressBar.progress = (item.amountAchieved / item.goal * 100).toInt()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ItemJarBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(jarsList[position])
    }

    override fun getItemCount(): Int {
        return jarsList.size
    }


}