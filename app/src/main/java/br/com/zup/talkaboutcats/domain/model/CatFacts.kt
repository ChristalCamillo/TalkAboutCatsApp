package br.com.zup.talkaboutcats.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "fatosdegatos")
@Parcelize
data class CatFacts(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_fact")
    var idFact: Long = 1,
    @ColumnInfo(name = "catfact")
    var catfact: String,
    var isFavorite: Boolean = false): Parcelable