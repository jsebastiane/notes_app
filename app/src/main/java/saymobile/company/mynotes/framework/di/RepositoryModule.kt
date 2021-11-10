package saymobile.company.mynotes.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides
import saymobile.company.core.repository.NoteRepository
import saymobile.company.mynotes.framework.RoomNoteDataSource

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}