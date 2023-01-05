package br.com.zup.talkaboutcats.data.datasource.local.dao

import androidx.room.*
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.domain.model.CatFacts

@Dao
interface CatFactsDAO {
    @Query("SELECT * FROM catfacts ORDER BY id ASC")
    fun getAllCatFacts(): List<CatFactsResult>

    @Query("SELECT * FROM catfacts WHERE isFavorite = 1")
    fun getAllCatFactsFavorited(): List<CatFactsResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCatFacts(listCatFacts: List<CatFactsResult>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCatFactsFavorite(catfacts: CatFactsResult)
}