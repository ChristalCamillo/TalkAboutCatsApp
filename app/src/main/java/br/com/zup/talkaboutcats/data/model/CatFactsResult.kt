package br.com.zup.talkaboutcats.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "catfacts")
@Parcelize
data class CatFactsResult(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @SerializedName("catfact")
    var catfact: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
