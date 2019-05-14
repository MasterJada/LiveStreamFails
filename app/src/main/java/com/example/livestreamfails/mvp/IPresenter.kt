package com.example.livestreamfails.mvp

interface IPresenter {
    fun getModel(): IModel
    fun updateView()
    fun setupView(view: IView)
    fun loadNext()
}