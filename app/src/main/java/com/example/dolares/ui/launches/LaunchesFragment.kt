package com.example.dolares.ui.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dolares.R
import com.example.dolares.databinding.LaunchesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment() {

    private val viewModel by viewModel<LaunchesViewModel>()
    private lateinit var binding: LaunchesFragmentBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    companion object {
        fun newInstance() = LaunchesFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.launches_fragment,
            container,
            false)

        init()
        adapterInit()
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadingStatus.observe(viewLifecycleOwner,{
            swipeRefreshLayout.isRefreshing = it
            if(it){
                binding.progressBar4.visibility = View.VISIBLE
            }else{
                binding.progressBar4.visibility = View.GONE
            }
        })

        viewModel.snackBarMessage.observe(viewLifecycleOwner,{
            Snackbar.make(swipeRefreshLayout,it,Snackbar.LENGTH_SHORT).show()
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel.selectedLaunchId.observe(viewLifecycleOwner,{
            it?.let{
                navigateDetailsScreen(it)
                viewModel.doneNavigateSelectedDetailsScreen()
            }
        })

    }

    private fun init() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        swipeRefreshLayout = binding.swipeRefreshLayoutLaunches
    }

    private fun adapterInit(){
        val launchItemClickListener = LaunchAdapter.LaunchItemClickListener{
            viewModel.navigateSelectedLaunchDetailsScreen(launchId = it.id)
        }
        val adapter = LaunchAdapter(launchItemClickListener)
        binding.launchesRV.adapter = adapter
    }

    private fun navigateDetailsScreen(launchId:String?){
        launchId?.let {
            findNavController().navigate(LaunchesFragmentDirections.actionLaunchesFragmentToLaunchDetailsFragment(launchId = launchId))
            return
        }
        Snackbar.make(swipeRefreshLayout,"Something Went Wrong",Snackbar.LENGTH_SHORT).show()

    }
}