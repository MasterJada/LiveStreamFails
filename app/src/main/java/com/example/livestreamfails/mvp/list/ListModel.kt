package com.example.livestreamfails.mvp.list

import com.example.livestreamfails.VideoModel
import com.example.livestreamfails.VideosDB
import com.example.livestreamfails.mvp.IModel
import com.example.livestreamfails.networking.ApiHelper
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync


class ListModel(val  presenter: ListPresenter): IModel {
    var page = 0
    val items = ArrayList<VideoModel>()
    override fun dataUpdated() {
        presenter.updateView()
    }
    override fun loadData() {
        loadMore()
    }


   private fun loadMore(){
       if(items.size / 20 == page) {
           doAsync {
               Observable.fromIterable(ApiHelper().getListJSoup(page = page))
                   .map {
                       ApiHelper().getInfoFromPage(it)
                   }
                   .buffer(5)
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribeOn(Schedulers.io())
                   .subscribe {
                       items.addAll(it)
                       VideosDB.videos = items
                       dataUpdated()
                   }
           }
       }else{
           dataUpdated()
       }
    }

    fun incrementPage(){
        page++
        loadMore()
    }



}