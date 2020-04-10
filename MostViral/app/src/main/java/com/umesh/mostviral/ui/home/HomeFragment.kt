package com.umesh.mostviral.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.umesh.mostviral.MainActivity
import com.umesh.mostviral.R
import com.umesh.mostviral.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private lateinit var mLayoutManager: StaggeredGridLayoutManager

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding
    private lateinit var pageLoader: PageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        mLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.memesList.layoutManager = mLayoutManager

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pageLoader = PageLoader(homeViewModel,mLayoutManager)
        binding.memesList.addOnScrollListener(pageLoader)

        homeViewModel.memes.observe(activity!!, Observer {
            pageLoader.loading = false
            if (binding.memesList.adapter == null){
//                Log.d(TAG,"Set adapter")
                binding.memesList.adapter = MemeAdapter(context!!,it,activity as MainActivity)
            }else{
//                Log.d(TAG,"Notify dataset changed ${it.size}")
                binding.memesList.adapter?.notifyDataSetChanged()
            }


        })
    }

}