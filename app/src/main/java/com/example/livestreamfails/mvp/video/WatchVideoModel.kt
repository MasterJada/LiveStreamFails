package com.example.livestreamfails.mvp.video

import com.example.livestreamfails.VideoModel
import com.example.livestreamfails.mvp.IModel

class WatchVideoModel(private val presenter: VideoPresenter) : IModel {
    private var videos = ArrayList<VideoModel>()
    private var currentVideo = 0


    fun setupItems(items: ArrayList<VideoModel>) {
        videos = items
    }

    fun setupPosition(pos: Int) {
        currentVideo = pos
    }

    override fun loadData() {
        dataUpdated()
    }

    fun loadNextVideo() {
        if (currentVideo + 1 < videos.size) {
            currentVideo++
            dataUpdated()
        }
    }

    fun loadPrevVideo() {
        if(currentVideo >= 1) {
            currentVideo--
            dataUpdated()
        }
    }

    override fun dataUpdated() {
        presenter.updateView()

    }

    fun getVideo(): VideoModel? = videos.getOrNull(currentVideo)


}