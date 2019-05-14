package com.example.livestreamfails.mvp.video

import com.example.livestreamfails.VideoModel
import com.example.livestreamfails.VideosDB
import com.example.livestreamfails.mvp.IPresenter
import com.example.livestreamfails.mvp.IView

class VideoPresenter private constructor() : IPresenter {
    private val model = WatchVideoModel(this)
    private lateinit var view: IView

    override fun updateView() {
        view.updateView(model)
    }

    override fun setupView(view: IView) {
        this.view = view
    }

    override fun loadNext() {
        model.loadData()
    }

    fun loadNextVideo() = model.loadNextVideo()
    fun loadPrevVideo() = model.loadPrevVideo()

    override fun getModel() = model

    fun setupItems( position: Int){
        model.setupItems(VideosDB.videos)
        model.setupPosition(position)
    }

    companion object {
        val instance: VideoPresenter by lazy {
            VideoPresenter()
        }
    }
}