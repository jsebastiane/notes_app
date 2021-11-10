package saymobile.company.mynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import saymobile.company.core.data.Note
import saymobile.company.core.repository.NoteRepository
import saymobile.company.core.usecase.AddNote
import saymobile.company.core.usecase.GetAllNotes
import saymobile.company.core.usecase.GetNote
import saymobile.company.core.usecase.RemoveNote
import saymobile.company.mynotes.framework.di.ApplicationModule
import saymobile.company.mynotes.framework.di.DaggerViewModelComponent
import javax.inject.Inject

/**
 * AndroidViewModel may become problematic for unit testing
 * Coroutines will be used to communicate with Room DB
 */
class NoteViewModel(application: Application): AndroidViewModel(application){
    //IO dispatcher is useful when communicating with some other data source
        //Retrofit-web, databases,  files on system
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val repository = NoteRepository(RoomNoteDataSource(application))

    @Inject
    lateinit var useCases: UseCases

    //Will inject into our lateinit var useCases variable
    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    //alert note fragment when its been loaded into the database
    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long){
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun deleteNote(note: Note){
        coroutineScope.launch {
            useCases.removeNote(note)
            saved.postValue(true)
        }
    }
}