package br.cericatto.devgrid

/**
 * AppConfiguration.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
object AppConfiguration {

    //----------------------------------------------
    // General Constants
    //----------------------------------------------

    const val BASE_URL = "https://api.github.com"
    const val TEST_GITHUB_USER = "octocat"
    const val TEST_PUBLIC_REPOS = 8

    // TODO: For safety reasons, not hardcoded values.
    const val TEST_LOGIN = "graffiti75"
    const val TEST_PASSWORD = "z4pp41974Apostrophe*"

    //----------------------------------------------
    // Logging
    //----------------------------------------------

    // Tag for common log output.
    const val TAG = "dev_grid"
}