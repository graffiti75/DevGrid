package br.cericatto.devgrid.presenter.presenter

import br.cericatto.devgrid.presenter.api.ApiService

/**
 * DetailPresenter.kt.
 * 
 * @author Rodrigo Cericatto
 * @since September 29, 2019
 */
interface DetailPresenter {
    fun getExtras(): String
    fun initDataSet(service: ApiService, userLogin: String, repoName: String)
    fun showData()
    fun showErrorMessage(error: String)
}