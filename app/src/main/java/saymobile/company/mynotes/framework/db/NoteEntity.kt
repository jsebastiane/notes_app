package saymobile.company.mynotes.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import saymobile.company.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    val title: String,
    val content: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long,

    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
){
    companion object{
        //Create a NoteEntity from not data
        //Does not need an instance of NoteEntity thus it is a companion object
        fun fromNote(note: Note) = NoteEntity(note.title, note.content, note.creationTime, note.updateTime, note.id)
    }

    //Return note data from NoteEntity
    fun toNote() = Note(title, content, creationTime, updateTime, id)
}