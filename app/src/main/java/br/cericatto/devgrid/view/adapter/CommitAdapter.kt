package br.cericatto.devgrid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.commit.GithubCommit
import br.cericatto.devgrid.view.activity.DetailActivity
import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * CommitAdapter.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 29, 2019
 */
class CommitAdapter(activity: DetailActivity, list: List<GithubCommit>) : RecyclerView.Adapter<CommitAdapter.CommitViewHolder>() {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private val mActivity = activity
    private var mCommitList = list

    //--------------------------------------------------
    // Adapter Methods
    //--------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CommitViewHolder(inflater.inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        var commit = mCommitList[position]
        var view = holder.itemView
        setTitle(view, commit)
    }

    override fun getItemCount(): Int = mCommitList.size

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun setTitle(view: View, commit: GithubCommit) {
        view.id_repo_title__text_view.text = commit.commit.message
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    inner class CommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}