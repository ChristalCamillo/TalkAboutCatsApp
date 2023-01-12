package br.com.zup.talkaboutcats.ui.likedfacts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.databinding.LikedFactItemBinding

class CatFactsFavoriteAdapter(
    private var catfactsList: MutableList<CatFactsResult> = mutableListOf(),
    private val catfactsClick: (catfacts: CatFactsResult) -> Unit
): RecyclerView.Adapter<CatFactsFavoriteAdapter.ViewHolder>() {

    class ViewHolder(val binding: LikedFactItemBinding): RecyclerView.ViewHolder(binding.root){
        fun showCharacter(catfacts: CatFactsResult){
            binding.tvCatFactLiked.text = catfacts.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LikedFactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catfacts = catfactsList[position]
        holder.showCharacter(catfacts)
        holder.binding.cvItemListaFavoritos.setOnClickListener {
            catfactsClick(catfacts)
        }
    }

    override fun getItemCount() = catfactsList.size

    fun updateFavoritedCatFactsList(newList: MutableList<CatFactsResult>){
        catfactsList = newList
        notifyDataSetChanged()
    }
}