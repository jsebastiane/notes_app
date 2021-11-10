package saymobile.company.mynotes.framework.di

import dagger.Component
import saymobile.company.mynotes.framework.ListViewModel
import saymobile.company.mynotes.framework.NoteViewModel

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}