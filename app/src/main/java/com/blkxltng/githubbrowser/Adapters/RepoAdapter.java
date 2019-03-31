package com.blkxltng.githubbrowser.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.R;

import java.util.List;

import javax.inject.Inject;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {

    public interface RepoAdapterCallback {
        void onClickRepo(String repoURL);
    }

    private static List<Repo> repoList;

    private Context mContext;

    private static RepoAdapterCallback mCallback;

//    private static int itemLimit = 3;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RepoAdapter(List<Repo> list, Context context) {
        repoList = list;
        mContext = context;
        mCallback = (RepoAdapterCallback) mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RepoAdapter.RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        return new RepoHolder(layoutInflater, parent);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
        holder.bind(repoList.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return repoList.size();
//        if(repoList.size() <= itemLimit) {
//            return repoList.size();
//        } else {
//            return itemLimit;
//        }
    }

    public static class RepoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView repoName, repoDescription, repoLanguage, repoDefaultBranch, repoStarCount;
        public RepoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_repo, parent, false));

            itemView.setOnClickListener(this);
            repoName = itemView.findViewById(R.id.listItemRepo_repoNameTextView);
            repoDescription = itemView.findViewById(R.id.listItemRepo_repoDescriptionTextView);
            repoLanguage = itemView.findViewById(R.id.listItemRepo_repoLanguageTextView);
            repoDefaultBranch = itemView.findViewById(R.id.listItemRepo_repoDefaultBranchTextView);
            repoStarCount = itemView.findViewById(R.id.listItemRepo_starCountTextView);
        }

        public void bind(Repo repo) {
            repoName.setText(repo.getRepoName());
            repoDescription.setText(repo.getRepoDescription());
            repoLanguage.setText(repo.getRepoLanguage());
            repoDefaultBranch.setText(repo.getRepoDefaultBranch());
            repoStarCount.setText(repo.getRepoStarCount().toString());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mCallback.onClickRepo(repoList.get(position).getRepoURL());
        }
    }

}
