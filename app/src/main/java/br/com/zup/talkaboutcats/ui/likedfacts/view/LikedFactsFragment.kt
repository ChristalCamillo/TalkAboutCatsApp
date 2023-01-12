package br.com.zup.talkaboutcats.ui.likedfacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.talkaboutcats.R
import br.com.zup.talkaboutcats.data.model.CatFactsResult
import br.com.zup.talkaboutcats.databinding.FragmentLikedFactsBinding
import br.com.zup.talkaboutcats.ui.likedfacts.viewmodel.LikedFactsViewModel
import br.com.zup.talkaboutcats.ui.viewstate.ViewState
import br.com.zup.talkaboutcats.utils.FACT_KEY

class LikedFactsFragment : Fragment() {

    private lateinit var binding: FragmentLikedFactsBinding

    private val viewModel: LikedFactsViewModel by lazy {
        ViewModelProvider(this)[LikedFactsViewModel::class.java]
    }

    private val favoritedCatFactsAdapter: CatFactsFavoriteAdapter by lazy {
        CatFactsFavoriteAdapter(mutableListOf(), this::goToCatFactsInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikedFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        showRecyclerView()
    }

    private fun initObserver(){
        viewModel.catfactsFavoriteState.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    favoritedCatFactsAdapter.updateFavoritedCatFactsList(it.data as MutableList<CatFactsResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun showRecyclerView(){
        binding.rvLikedFactsList.adapter = favoritedCatFactsAdapter
        binding.rvLikedFactsList.layoutManager = LinearLayoutManager(context)
    }

    private fun goToCatFactsInfo(catfacts: CatFactsResult){
        val bundle = bundleOf(FACT_KEY to catfacts)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_likedFactsFragment_to_factDetailsFragment, bundle
        )
    }
}