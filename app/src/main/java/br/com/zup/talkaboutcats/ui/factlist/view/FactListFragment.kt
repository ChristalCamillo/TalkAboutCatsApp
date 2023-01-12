package br.com.zup.talkaboutcats.ui.factlist.view

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
import br.com.zup.talkaboutcats.databinding.FragmentFactListBinding
import br.com.zup.talkaboutcats.ui.factlist.viewmodel.CatFactsListViewModel
import br.com.zup.talkaboutcats.ui.viewstate.ViewState
import br.com.zup.talkaboutcats.utils.FACT_KEY

class FactListFragment : Fragment() {
    private lateinit var binding: FragmentFactListBinding

    private val viewModel: CatFactsListViewModel by lazy {
        ViewModelProvider(this)[CatFactsListViewModel::class.java]
    }

    private val catFactsAdapter: CatFactsAdapter by lazy {
        CatFactsAdapter(mutableListOf(), this::goToCatFactsDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRvCatFactsList()
        viewModel.getAllCatFactsNetwork()
        initObserver()

        binding.button.setOnClickListener {
            goToCatFactsFavorite()
        }
    }

    private fun initObserver(){
        viewModel.catFactsListState.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    catFactsAdapter.updateCatFactsList(it.data as MutableList<CatFactsResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    private fun setUpRvCatFactsList() {
        binding.rvFactsList.adapter = catFactsAdapter
        binding.rvFactsList.layoutManager = LinearLayoutManager(context)
    }

    private fun goToCatFactsDetail(catfacts: CatFactsResult) {
        val bundle = bundleOf(FACT_KEY to catfacts)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_factListFragment_to_factDetailsFragment, bundle
        )
    }

    private fun goToCatFactsFavorite() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_factListFragment_to_likedFactsFragment
        )
    }
}