package br.com.zup.talkaboutcats.ui.factdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.zup.talkaboutcats.R
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.databinding.FragmentFactDetailBinding
import br.com.zup.talkaboutcats.ui.factdetails.viewmodel.FactDetailViewModel
import br.com.zup.talkaboutcats.utils.FACT_KEY

class FactDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFactDetailBinding
    private lateinit var catfacts: CatFactsResult

    private val viewModel: FactDetailViewModel by lazy {
        ViewModelProvider(this)[FactDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

        binding.likeButton.setOnClickListener {
            catfacts.isFavorite = !catfacts.isFavorite
            updateFavoriteIconColor()
            viewModel.updateCatFactsFavorite(catfacts)
            showFavoriteUpdateToast()
        }
    }

    private fun getData(){

        val data = arguments?.getParcelable<CatFactsResult>(FACT_KEY)

        if(data != null){
            catfacts = data

            catfacts.let {
                binding.tvMeowfactDetail.text = it.toString()
                updateFavoriteIconColor()
            }
        }else{
            Toast.makeText(context, "Não foi possível carregar o fato", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateFavoriteIconColor(){
        binding.likeButton.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if (catfacts.isFavorite) R.drawable.ic_heart_like
                else R.drawable.ic_heart_dislike
            )
        )
    }

    private fun showFavoriteUpdateToast(){
        if(catfacts.isFavorite){
            Toast.makeText(context, "${catfacts.id} foi favoritado com sucesso!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context, "${catfacts.id} foi desfavoritado", Toast.LENGTH_SHORT).show()
        }
    }
}