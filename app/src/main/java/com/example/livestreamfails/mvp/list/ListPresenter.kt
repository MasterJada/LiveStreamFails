package com.example.livestreamfails.mvp.list

import com.example.livestreamfails.mvp.IPresenter
import com.example.livestreamfails.mvp.IView

class ListPresenter private constructor() : IPresenter {
    private val model = ListModel(this)
    private lateinit var view: IView
    override fun setupView(v: IView) {
        view = v
    }

    override fun updateView() {
        view.updateView(model)
    }
    override fun loadNext() {
        model.loadData()
    }
    override fun getModel() = model

    companion object {
        val instance: ListPresenter by lazy {
            ListPresenter()
        }
    }
}