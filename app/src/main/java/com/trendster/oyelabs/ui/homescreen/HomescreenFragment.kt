package com.trendster.oyelabs.ui.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
    private lateinit var topOfferAdapter: TopOfferAdapter
    private lateinit var exclusiveOfferAdapter: OfferAdapter
    private lateinit var groceriesAdapter: GroceriesAdapter
    private lateinit var bestSellingAdapter: OfferAdapter
    private val viewModel: HomeScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

    private fun setupTopOfferRecycler() {
        topOfferAdapter = TopOfferAdapter()
        binding.rvTop.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = topOfferAdapter
            val pagerSnapHelper = PagerSnapHelper()
            pagerSnapHelper.attachToRecyclerView(binding.rvTop)
            binding.indicator.attachToRecyclerView(binding.rvTop, pagerSnapHelper)
            topOfferAdapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
        }
    }

    private fun setupExclusiveRecycler() {
        exclusiveOfferAdapter = OfferAdapter()
        binding.rvExclusiveOffer.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = exclusiveOfferAdapter
        }
    }

    private fun setupGroceriesRecycler() {
        groceriesAdapter = GroceriesAdapter()
        binding.rvGroceries.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = groceriesAdapter
        }
    }

    private fun setupBestSellingRecycler() {
        bestSellingAdapter = OfferAdapter()
        binding.rvBestSelling.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = bestSellingAdapter
        }
    }

    private fun setupObservers() {
        viewModel.topOfferResponse.observe(
            viewLifecycleOwner,
            {
                topOfferAdapter.setData(it)
            }
        )
        viewModel.exclusiveOfferResponse.observe(
            viewLifecycleOwner,
            {
                exclusiveOfferAdapter.setData(it)
            }
        )
        viewModel.groceriesResponse.observe(
            viewLifecycleOwner,
            {
                groceriesAdapter.setData(it)
            }
        )
        viewModel.bestSellingResponse.observe(
            viewLifecycleOwner,
            {
                bestSellingAdapter.setData(it)
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
