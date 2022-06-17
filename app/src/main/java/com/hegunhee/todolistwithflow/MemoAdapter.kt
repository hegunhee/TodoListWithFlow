package com.hegunhee.todolistwithflow

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.databinding.MemoItemBinding

class MemoAdapter(
    private var memoList: List<MemoEntity>,
    val onMemoClick : (MemoEntity) -> Unit
    ) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    inner class MemoViewHolder(
        private val binding: MemoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(memo : MemoEntity) = with(binding){
            title.text = memo.text
            deleteButton.setOnClickListener{
                onMemoClick(memo)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(MemoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bindView(memoList[position])
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
    fun setData(list: List<MemoEntity>){
        memoList = list
        notifyDataSetChanged()
    }
}