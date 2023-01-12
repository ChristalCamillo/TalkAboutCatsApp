package br.com.zup.talkaboutcats.ui.factlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.databinding.FactItemBinding

class CatFactsAdapter(
    private var catFactsList: MutableList<CatFactsResult> = mutableListOf(),
    private val catFactsClick: (catFacts: CatFactsResult) -> Unit
): RecyclerView.Adapter<CatFactsAdapter.ViewHolder>() {

    class ViewHolder(val binding: FactItemBinding): RecyclerView.ViewHolder(binding.root){
        fun showCatFacts(catFacts: CatFactsResult){
            binding.tvCatFact.text = catFacts.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catFacts = catFactsList[position]
        holder.showCatFacts(catFacts)
        holder.binding.cvItemLista.setOnClickListener {
            catFactsClick(catFacts)
        }
    }

    override fun getItemCount() = catFactsList.size


    fun updateCatFactsList(newList: MutableList<CatFactsResult>){
        catFactsList = newList
        notifyDataSetChanged()
    }
}