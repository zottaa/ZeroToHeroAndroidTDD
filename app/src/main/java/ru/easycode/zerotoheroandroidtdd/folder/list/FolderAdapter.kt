package ru.easycode.zerotoheroandroidtdd.folder.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemFolderListRecyclerViewBinding
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderUi

class FolderAdapter(
    private val folderDetails: FolderDetails,
    private var list: List<FolderUi> = emptyList()
) : RecyclerView.Adapter<FolderListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderListViewHolder {
        val binding = ItemFolderListRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return FolderListViewHolder(binding, folderDetails)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FolderListViewHolder, position: Int) {
        holder.hold(list[position])
    }

    fun update(newList: List<FolderUi>) {
        val diffUtil = FolderListDiffUtil(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }
}

class FolderListViewHolder(
    private val binding: ItemFolderListRecyclerViewBinding,
    private val folderDetails: FolderDetails
) : RecyclerView.ViewHolder(binding.root) {

    fun hold(folderUi: FolderUi) {
        folderUi.apply {
            showTitle(binding.folderTitleTextView)
            showNotesCount(binding.folderCountTextView)
            binding.folderLinearLayout.setOnClickListener {
                folderDetails.folderDetails(folderUi)
            }
        }
    }
}

class FolderListDiffUtil(
    private val oldList: List<FolderUi>,
    private val newList: List<FolderUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == (newList[newItemPosition])
}