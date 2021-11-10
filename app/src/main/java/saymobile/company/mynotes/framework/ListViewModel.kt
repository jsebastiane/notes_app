package saymobile.company.mynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import saymobile.company.core.data.Note
import saymobile.company.mynotes.framework.di.ApplicationModule
import saymobile.company.mynotes.framework.di.DaggerViewModelComponent
import javax.inject.Inject

class ListViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getAllNotes(){
        coroutineScope.launch{
            val list = useCases.getAllNotes()
            //When we retrieve each note we assign the word count to getWordCount
            list.forEach { it.wordCount = useCases.getWordCount.invoke(it)}
            notes.postValue(list)
        }
    }
}