package com.hegunhee.todolistwithflow.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.databinding.MemoItemBinding

class MemoAdapter(
    val deleteMemo: (MemoEntity) -> Unit,
    val insertMemo : (MemoEntity) -> Unit
) : ListAdapter<MemoEntity,MemoAdapter.MemoViewHolder>(diffUtil) {

    inner class MemoViewHolder(
        private val binding: MemoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(memo: MemoEntity) = with(binding) {
            title.text = memo.text
            deleteButton.setOnClickListener {
                deleteMemo(memo)
            }
            check.visibility = if (memo.isCheck) View.VISIBLE else View.GONE
            title.setOnClickListener{
                insertMemo(memo.copy(isCheck = !memo.isCheck))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(MemoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}

internal object diffUtil : DiffUtil.ItemCallback<MemoEntity>(){
    override fun areItemsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean =
        oldItem.text == newItem.text

    override fun areContentsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean =
        oldItem == newItem
}