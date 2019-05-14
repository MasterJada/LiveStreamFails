package com.example.livestreamfails.mvp.video


import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.livestreamfails.R
import com.example.livestreamfails.mvp.IModel
import com.example.livestreamfails.mvp.IView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_watch_video.*



class WatchVideoFragment : Fragment(), IView, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {



    override fun updateView(model: IModel) {
        if(model is WatchVideoModel){
            iv_cover.visibility = View.VISIBLE
            videoView.stopPlayback()
            videoView.setVideoURI(Uri.parse(model.getVideo()?.videoLink))
            tv_description.text = model.getVideo()?.videoTitle
            Picasso.get().load(model.getVideo()?.imageLink)?.into(iv_cover)
        }
    }

    private val presenter = VideoPresenter.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.setupView(this)
        return inflater.inflate(R.layout.fragment_watch_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoView.setOnPreparedListener(this)
        videoView.setOnCompletionListener(this)
        videoView.setOnErrorListener(this)
        presenter.loadNext()
        bt_back.setOnClickListener {
            presenter.loadPrevVideo()
        }
        bt_next.setOnClickListener {
            presenter.loadNextVideo()
        }

    }
    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        presenter.loadNextVideo()
        return true
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.let {mediaPlayer ->
            val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
            val screenRatio = videoView.width / videoView.height.toFloat()
            val scaleX = videoRatio / screenRatio
            if (scaleX >= 1f) {
                videoView.scaleX = scaleX
            } else {
                videoView.scaleY = 1f / scaleX
            }
        }
        iv_cover.visibility = View.GONE
        videoView.start()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        presenter.loadNextVideo()
    }

    fun setupItemsAndPos(pos: Int) = presenter.setupItems(pos)

}
