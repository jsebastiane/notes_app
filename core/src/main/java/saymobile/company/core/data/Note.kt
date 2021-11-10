package saymobile.company.core.data

data class Note (
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    //The id this note will have in the database
    var id: Long = 0,
    var wordCount: Int = 0
)