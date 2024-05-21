package com.hegunhee.todolistwithflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.databinding.MemoItemBinding

class MemoAdapter(
    private val eventHandler : MainActivityActionHandler
) : ListAdapter<MemoEntity,MemoAdapter.MemoViewHolder>(diffUtil) {

    inner class MemoViewHolder(
        private val binding: MemoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(memo: MemoEntity) = with(binding) {
            memoEntity = memo
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(MemoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            eventHandler = this@MemoAdapter.eventHandler
        })
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}

internal object diffUtil : DiffUtil.ItemCallback<MemoEntity>(){
    override fun areItemsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
        return oldItem.text == newItem.text
    }



    override fun areContentsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
        return oldItem == newItem
    }
}