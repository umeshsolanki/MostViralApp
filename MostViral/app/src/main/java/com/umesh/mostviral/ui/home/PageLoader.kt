package com.umesh.mostviral.ui.home

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class PageLoader(val homeViewModel: HomeViewModel,
                 val mLayoutManager:StaggeredGridLayoutManager) : RecyclerView.OnScrollListener() {

    private var page = 1
    private var visibleItemCount:Int = 0
    internal var loading = false
    private var totalItemCount = 0
    private var pastVisibleItems = 0
    private val TAG = "PageLoader"


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = mLayoutManager.childCount
        totalItemCount = mLayoutManager.itemCount


        var firstVisibleItems: IntArray? = null
        firstVisibleItems = mLayoutManager.findFirstVisibleItemPositions(firstVisibleItems)
        if (firstVisibleItems != null && firstVisibleItems.size > 0) {
            pastVisibleItems = firstVisibleItems[0]
        }
//        Log.d(TAG, "Count: ${visibleItemCount}, ${totalItemCount}, $pastVisibleItems")
        if (!loading) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                loading = true
                Log.d(TAG, "Load next page: ${page+1}")
                homeViewModel.fetch(++page)
            }
        }
    }
}