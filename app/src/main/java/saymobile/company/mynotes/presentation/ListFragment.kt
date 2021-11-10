package saymobile.company.mynotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import saymobile.company.mynotes.R
import saymobile.company.mynotes.databinding.FragmentListBinding
import saymobile.company.mynotes.framework.ListViewModel

class ListFragment : Fragment(), ListAction {

    private val notesListAdapter = NoteListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        binding.addNote.setOnClickListener { goToNoteDetails() }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
//        viewModel.getAllNotes()



    }

    private fun observeViewModel(){
        viewModel.notes.observe(viewLifecycleOwner, Observer {notesList ->
            binding.loadingView.visibility = View.GONE
            binding.notesListView.visibility = View.VISIBLE
            notesListAdapter.updateNotes(notesList.sortedBy { it.updateTime })
        })
    }

    private fun goToNoteDetails(id: Long = 0L){
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.root).navigate(action)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNotes()
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }



}