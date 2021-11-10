package saymobile.company.mynotes.framework.di

import dagger.Module
import dagger.Provides
import saymobile.company.core.repository.NoteRepository
import saymobile.company.core.usecase.*
import saymobile.company.mynotes.framework.UseCases

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        //getWordCount is not stored in database it works on the data
        GetWordCount()
    )
}