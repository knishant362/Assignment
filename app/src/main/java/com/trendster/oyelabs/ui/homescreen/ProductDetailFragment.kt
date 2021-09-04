package com.trendster.oyelabs.ui.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.trendster.oyelabs.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    val args: ProductDetailFragmentArgs by navArgs()
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        val grocerieItem = args.item
        binding.txtItemName.text = grocerieItem.title
        binding.txtItemDesc.text = grocerieItem.description
        binding.imgGroceri.load(grocerieItem.img)
        setupClickListeners()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.imgShare.setOnClickListener {
            Toast.makeText(requireContext(), "Share", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
