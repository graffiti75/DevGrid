package br.cericatto.devgrid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.extensions.openActivityExtras
import br.cericatto.devgrid.view.activity.DetailActivity
import br.cericatto.devgrid.view.activity.MainActivity
import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * RepoAdapter.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 29, 2019
 */
class RepoAdapter(activity: MainActivity, list: List<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private val mActivity = activity
    private var mRepoList = list

    //--------------------------------------------------
    // Adapter Methods
    //--------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepoViewHolder(inflater.inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        var repo = mRepoList[position]
        var view = holder.itemView
        setTitle(view, repo)
    }

    override fun getItemCount(): Int = mRepoList.size

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun setTitle(view: View, repo: Repo) {
        view.id_repo_title__text_view.text = repo.name
        view.id_repo_title__text_view.setOnClickListener {
            mActivity.openActivityExtras(mActivity, DetailActivity::class.java,
                AppConfiguration.REPO_NAME_EXTRA, repo.name)
        }
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}