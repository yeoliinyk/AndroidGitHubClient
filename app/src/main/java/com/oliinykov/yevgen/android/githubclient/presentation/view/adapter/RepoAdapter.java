package com.oliinykov.yevgen.android.githubclient.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oliinykov.yevgen.android.githubclient.R;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;

import java.util.Collection;
import java.util.List;

/**
 * Adapter that manages a collection of {@link Repo}.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<Repo> mReposCollection;
    private Context mContext;

    public RepoAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_repo, parent, false);
        return new RepoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        final Repo repo = mReposCollection.get(position);
        String description = repo.getDescription();
        if (description == null) {
            description = mContext.getString(R.string.prompt_no_repo_desc);
        }
        holder.titleTextView.setText(repo.getTitle());
        holder.descTextView.setText(description);
    }

    @Override
    public int getItemCount() {
        return (mReposCollection != null) ? mReposCollection.size() : 0;
    }

    public void setReposCollection(Collection<Repo> reposCollection) {
        validateReposCollection(reposCollection);
        mReposCollection = (List<Repo>) reposCollection;
        notifyDataSetChanged();
    }

    private void validateReposCollection(Collection<Repo> reposCollection) {
        if (reposCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView descTextView;

        public RepoViewHolder(View itemView) {
            super(itemView);
            this.titleTextView = (TextView) itemView.findViewById(R.id.tv_repo_title);
            this.descTextView = (TextView) itemView.findViewById(R.id.tv_repo_desc);
        }
    }
}
