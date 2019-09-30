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
    const val COMMITS_NUMBER = 5

    const val TEST_GITHUB_USER = "octocat"
    const val TEST_PUBLIC_REPOS = 8

    // TODO: Just a reminder -> For safety reasons, not hardcoded values.
    const val TEST_LOGIN = "graffiti75"
    const val TEST_PASSWORD = "z4pp41974Apostrophe*"

    //----------------------------------------------
    // Extras
    //----------------------------------------------

    const val REPO_NAME_EXTRA = "repo_name_extra"
    const val QRCODE_LOGIN_EXTRA = "qrcode_login_extra"

    //----------------------------------------------
    // Logging
    //----------------------------------------------

    // Tag for common log output.
    const val TAG = "dev_grid"
}