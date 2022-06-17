package com.hegunhee.todolistwithflow.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.databinding.MemoItemBinding

class MemoAdapter(
    private var memoList: List<MemoEntity>,
    val deleteMemo: (MemoEntity) -> Unit,
    val insertMemo : (MemoEntity) -> Unit
) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

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
        return MemoViewHolder(
            MemoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bindView(memoList[position])
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    fun setData(list: List<MemoEntity>) {
        memoList = list
        notifyDataSetChanged()
    }
}