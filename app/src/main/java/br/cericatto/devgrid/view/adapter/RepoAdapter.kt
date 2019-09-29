package br.cericatto.devgrid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.Repo
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
        setCardView(view, repo)
    }

    override fun getItemCount(): Int = mRepoList.size

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun setTitle(view: View, repo: Repo) {
        view.id_repo_title__text_view.text = repo.name
    }

    private fun setCardView(view: View, repo: Repo) {
        view.id_repo_title__text_view.setOnClickListener {
//            mActivity.openActivityExtras(mActivity, DetailActivity::class.java,
//                AppConfiguration.REPO_ID_EXTRA, repo.id)
            Toast.makeText(mActivity, "Clicked", Toast.LENGTH_LONG).show()
        }
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}