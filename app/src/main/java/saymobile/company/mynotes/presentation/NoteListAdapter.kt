package saymobile.company.mynotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import saymobile.company.core.data.Note
import saymobile.company.mynotes.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(var notesList: ArrayList<Note>, val actions: ListAction): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>){
        notesList.clear()
        notesList.addAll(newNotes)
        //Look into creating more specific events so we don't have to refresh the entire
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       with(holder){
           with(notesList[position]){
               binding.title.text = title
               binding.content.text = content

               val sdf = SimpleDateFormat("dd MMM, HH:mm:ss")
               val resultDate = Date(updateTime)
               binding.date.text = "Last updates: ${sdf.format(resultDate)}"
               binding.root.setOnClickListener{
                   actions.onClick(this.id)
               }
               binding.wordCount.text = "Words: $wordCount"
           }
       }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}