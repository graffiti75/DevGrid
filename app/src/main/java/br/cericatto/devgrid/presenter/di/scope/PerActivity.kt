package br.cericatto.devgrid.presenter.di.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * PerActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PerActivity