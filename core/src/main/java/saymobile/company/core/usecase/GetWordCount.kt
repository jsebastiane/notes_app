package saymobile.company.core.usecase

import saymobile.company.core.data.Note

class GetWordCount {
    operator fun invoke(note: Note) = getCount(note.content)

    private fun getCount(str: String) =
        str.split(" ", "\n")
            .filter {
                //getting any words - this would not count any numbers like "14" or punctuation
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()
}