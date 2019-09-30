package br.cericatto.devgrid.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.cericatto.devgrid.databinding.ItemCommitBinding
import br.cericatto.devgrid.model.commit.GithubCommit
import br.cericatto.devgrid.view.activity.DetailActivity

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

    /*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CommitViewHolder(inflater.inflate(R.layout.item_commit, parent, false))
    }
    */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommitBinding.inflate(inflater)
        return CommitViewHolder(binding)
    }

    /*
    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        var commit = mCommitList[position]
        var view = holder.itemView
        setTexts(view, commit)
    }
     */

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) = holder.bind(mCommitList[position])

    override fun getItemCount(): Int = mCommitList.size

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    /*
    private fun setTexts(view: View, commit: GithubCommit) {
        view.id_item_commit__name_text_view.text = commit.commit.author.name
        view.id_item_commit__email_text_view.text = commit.commit.author.email
        view.id_item_commit__date_text_view.text = commit.commit.author.date
        view.id_item_commit__message_text_view.text = commit.commit.message
        view.id_item_commit__sha_text_view.text = commit.sha
    }
     */

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

//    inner class CommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class CommitViewHolder(private val binding: ItemCommitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubCommit) {
            binding.commit = item
            binding.executePendingBindings()
        }
    }
}