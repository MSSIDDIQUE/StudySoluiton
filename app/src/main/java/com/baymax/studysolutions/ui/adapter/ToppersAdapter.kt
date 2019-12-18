package com.baymax.studysolutions.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.baymax.studysolutions.R
import com.baymax.studysolutions.data.Topper
import com.baymax.studysolutions.databinding.TopperItemViewBinding
import com.baymax.studysolutions.ui.TopperListener
import kotlin.collections.ArrayList

class ToppersAdapter(private var toppers:ArrayList<Topper>,private val standard:String):RecyclerView.Adapter<ToppersAdapter.TopperViewHolder>(),
    TopperListener {


    init {
        toppers = filteredToppers(toppers,standard)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopperViewHolder {
        val binding: TopperItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.topper_item_view,parent,false)
        return TopperViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        Log.d("(Saquib)","size of the list is "+toppers.size)
        return toppers.size
    }

    override fun onBindViewHolder(holder: TopperViewHolder, position: Int) {
        val topper = toppers.get(position)
        holder.bind(topper)
        holder.bind.onClickListener
    }

    override fun onClick(topper: Topper) {
    }

    class TopperViewHolder(val bind:TopperItemViewBinding):RecyclerView.ViewHolder(bind.root) {
        fun bind(obj:Topper)
        {
            bind.model = obj
            bind.executePendingBindings()
        }
    }

    fun filteredToppers(toppers:ArrayList<Topper>, standard: String):ArrayList<Topper>
    {
        val filteredList =ArrayList<Topper>()
        for(topper in toppers)
        {
            if(topper.standard == standard)
            {
                filteredList.add(topper)
                Log.d("(Saquib)","data added "+topper.name)
            }
        }
        return filteredList
    }
}