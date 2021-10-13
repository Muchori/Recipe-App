package com.joseph.muchori.recipeapp.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.muchori.recipeapp.viewModels.MainViewModel
import com.joseph.muchori.recipeapp.R
import com.joseph.muchori.recipeapp.adapters.RecipesAdapter
import com.joseph.muchori.recipeapp.databinding.FragmentRecipesBinding
import com.joseph.muchori.recipeapp.util.Constants.Companion.API_KEY
import com.joseph.muchori.recipeapp.util.NetworkResult
import com.joseph.muchori.recipeapp.util.observeOnce
import com.joseph.muchori.recipeapp.viewModels.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val args by navArgs<RecipesFragmentArgs>()
    
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    
    

    private lateinit var mainViewModel : MainViewModel
    private lateinit var recipeViewModel: RecipeViewModel
    private val mAdapter by lazy { RecipesAdapter () }
//    private lateinit var binding: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipeViewModel = ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setUpRecyclerView()
        readDatabase()

        binding.recipesFab.setOnClickListener{
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
        }


        return binding.root
    }

    private fun setUpRecyclerView(){
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, {
                    database ->
                if (database.isNotEmpty() && !args.backFromBottomSheet){
                    Log.d("RecipeFragment", "readDatabase called")
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()

                }else{
                    requestApiData()
                }
            })
        }
    }

    private fun requestApiData(){
        Log.d("RecipeFragment", "requestApiData called")
        mainViewModel.getRecipes(recipeViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, {
            response -> when (response){
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(),response.message.toString(), Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun loadDataFromCache(){
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, {
                    database ->
                if (database.isNotEmpty()){
                    mAdapter.setData(database[0].foodRecipe)
                }
            })
        }
    }

    private fun showShimmerEffect(){
        binding.recyclerview.showShimmer()
    }
    private fun hideShimmerEffect(){
        binding.recyclerview.hideShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}