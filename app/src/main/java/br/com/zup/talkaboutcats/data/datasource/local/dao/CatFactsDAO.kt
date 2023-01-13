package br.com.zup.talkaboutcats.data.datasource.local.dao

import androidx.room.*
import br.com.zup.talkaboutcats.data.model.CatFactsResult

@Dao
interface CatFactsDAO {
    @Query("SELECT * FROM catfacts ORDER BY id ASC")
    fun getAllCatFacts(): List<CatFactsResult>

    @Query("SELECT * FROM catfacts WHERE isFavorite = 1")
    fun getAllCatFactsFavorited(): List<CatFactsResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCatFacts(listCatFacts: List<CatFactsResult>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCatFact(catFact: CatFactsResult):Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCatFactsFavorite(catfacts: CatFactsResult)
}