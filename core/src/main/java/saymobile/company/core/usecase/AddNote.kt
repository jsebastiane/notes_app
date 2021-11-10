package saymobile.company.core.usecase

import saymobile.company.core.data.Note
import saymobile.company.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}