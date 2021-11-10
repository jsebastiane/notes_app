package saymobile.company.core.repository

import saymobile.company.core.data.Note

class NoteRepository (private val dataSource: NoteDataSource) {
    //addNote makes use of interface functionality from NoteDataSource
    //The NoteDataSource functions are overridden by RoomNoteDataSource and given additional
    //functionality
    //So when dataSource.add(note) is called it is executing the overridden function in
    //RoomNoteDataSource in which the note is added as an Entity to the Room database
    suspend fun addNote(note: Note) = dataSource.add(note)

    suspend fun getNote(id: Long) = dataSource.get(id)

    suspend fun getAllNotes() = dataSource.getAll()

    suspend fun removeNote(note: Note) = dataSource.remove(note)
}