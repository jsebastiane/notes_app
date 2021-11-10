package saymobile.company.mynotes.framework

import android.content.Context
import saymobile.company.core.data.Note
import saymobile.company.core.repository.NoteDataSource
import saymobile.company.mynotes.framework.db.DatabaseService
import saymobile.company.mynotes.framework.db.NoteEntity

/**
 * Communicates with NoteDao -  implements NoteDataSource interface
 */
class RoomNoteDataSource(context: Context): NoteDataSource {

    //This will take application context when called from ViewModel
    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map { it.toNote() }

    //Deletes a NoteEntity so we use NoteEntity.fromNote(note) to convert our note to a NoteEntity
    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}