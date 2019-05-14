package com.example.livestreamfails.mvp.list


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.livestreamfails.MainActivity

import com.example.livestreamfails.R
import com.example.livestreamfails.mvp.IModel
import com.example.livestreamfails.mvp.IView
import com.example.livestreamfails.mvp.video.WatchVideoFragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), IView {
    private val presenter = ListPresenter.instance
    private val adapter = VideoAdapters()
    private val handler = Handler()

    override fun updateView(model: IModel) {
        if(model is ListModel){
            handler.post {
                adapter.items = model.items
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.setupView(this)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_items.adapter = adapter
        rv_items.layoutManager = GridLayoutManager(context, 4)
        presenter.loadNext()
        adapter.setOnItemClick {pos ->
            val fragment = WatchVideoFragment()
            fragment.setupItemsAndPos(pos)
            (activity as? MainActivity)?.changeFragment(fragment, true)
        }
    }

}
