package br.cericatto.devgrid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.commit.GithubCommit
import br.cericatto.devgrid.view.activity.DetailActivity
import kotlinx.android.synthetic.main.item_commit.view.*

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
        return CommitViewHolder(inflater.inflate(R.layout.item_commit, parent, false))
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        var commit = mCommitList[position]
        var view = holder.itemView
        setTexts(view, commit)
    }

    override fun getItemCount(): Int = mCommitList.size

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun setTexts(view: View, commit: GithubCommit) {
        view.id_commit_name__text_view.text = commit.commit.author.name
        view.id_commit_email__text_view.text = commit.commit.author.email
        view.id_commit_date__text_view.text = commit.commit.author.date
        view.id_commit_message__text_view.text = commit.commit.message
        view.id_commit_sha__text_view.text = commit.sha
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    inner class CommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}