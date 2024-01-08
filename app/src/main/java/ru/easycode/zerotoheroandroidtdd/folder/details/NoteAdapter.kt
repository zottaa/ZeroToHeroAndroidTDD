package ru.easycode.zerotoheroandroidtdd.folder.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemNoteRecyclerViewBinding

class NoteAdapter(
    private val editNote: EditNote,
    private var list: List<NoteUi> = emptyList()
) : RecyclerView.Adapter<NoteListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding = ItemNoteRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return NoteListViewHolder(binding, editNote)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.hold(list[position])
    }

    fun update(newList: List<NoteUi>) {
        val diffUtil = NoteListDiffUtil(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }
}

class NoteListViewHolder(
    private val binding: ItemNoteRecyclerViewBinding,
    private val editNote: EditNote
) : RecyclerView.ViewHolder(binding.root) {
    fun hold(noteUi: NoteUi) {
        noteUi.apply {
            showTitle(binding.noteTitleTextView)
        }
        binding.noteTitleTextView.setOnClickListener {
            noteUi.edit(editNote)
        }
    }
}

class NoteListDiffUtil(
    private val oldList: List<NoteUi>,
    private val newList: List<NoteUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == (newList[newItemPosition])
}