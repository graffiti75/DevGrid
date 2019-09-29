package br.cericatto.devgrid.presenter.di.component

import br.cericatto.devgrid.presenter.di.module.MainModule
import br.cericatto.devgrid.presenter.di.scope.PerActivity
import br.cericatto.devgrid.view.activity.MainActivity
import dagger.Component

/**
 * MainComponent.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
@PerActivity
@Component(modules = [MainModule::class], dependencies = [ApplicationComponent::class])
interface MainComponent {
    fun inject(target: MainActivity)
}