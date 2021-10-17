package com.trendster.oyelabs.ui.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.trendster.oyelabs.adapters.GroceriesAdapter
import com.trendster.oyelabs.adapters.OfferAdapter
import com.trendster.oyelabs.adapters.TopOfferAdapter
import com.trendster.oyelabs.databinding.FragmentHomescreenBinding
import com.trendster.oyelabs.viewmodel.HomeScreenViewModel

class HomescreenFragment : Fragment() {

    private var _binding: FragmentHomescreenBinding? = null
    private val binding get() = _binding!!

    private val topOfferAdapter by lazy { TopOfferAdapter() }
    private val exclusiveOfferAdapter by lazy { OfferAdapter() }
    private val groceriesAdapter by lazy {
        GroceriesAdapter {
            val action = HomescreenFragmentDirections
                .actionHomescreenFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }
    }
    private val bestSellingAdapter by lazy { OfferAdapter() }

    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomescreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTopOfferRecycler()
        setupExclusiveRecycler()
        setupGroceriesRecycler()
        setupBestSellingRecycler()
        fetchData()
        setupObservers()
    }

    private fun setupTopOfferRecycler() = binding.apply {
        rvTop.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = topOfferAdapter
            val pagerSnapHelper = PagerSnapHelper()
            pagerSnapHelper.attachToRecyclerView(rvTop)
            indicator.attachToRecyclerView(rvTop, pagerSnapHelper)
            topOfferAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        }
    }

    private fun setupExclusiveRecycler() = binding.rvExclusiveOffer.apply {
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = exclusiveOfferAdapter
    }

    private fun setupGroceriesRecycler() = binding.rvGroceries.apply {
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = groceriesAdapter
    }

    private fun setupBestSellingRecycler() = binding.rvBestSelling.apply {
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = bestSellingAdapter
    }

    private fun setupObservers() {
        viewModel.topOfferResponse.observe(
            viewLifecycleOwner,
            {
                topOfferAdapter.submitList(it)
            }
        )
        viewModel.exclusiveOfferResponse.observe(
            viewLifecycleOwner,
            {
                exclusiveOfferAdapter.submitList(it)
            }
        )
        viewModel.groceriesResponse.observe(
            viewLifecycleOwner,
            {
                groceriesAdapter.submitList(it)
            }
        )
        viewModel.bestSellingResponse.observe(
            viewLifecycleOwner,
            {
                bestSellingAdapter.submitList(it)
            }
        )
    }

    private fun fetchData() {
        viewModel.fetchTopOffers()
        viewModel.fetchExclusiveOffers()
        viewModel.fetchGroceries()
        viewModel.fetchBestSelling()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
