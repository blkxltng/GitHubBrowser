package com.blkxltng.githubbrowser.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blkxltng.githubbrowser.Adapters.RepoAdapter;
import com.blkxltng.githubbrowser.Constants;
import com.blkxltng.githubbrowser.Dagger.Component.ApplicationComponent;
import com.blkxltng.githubbrowser.GitHubBrowserApplication;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;
import com.blkxltng.githubbrowser.Models.Organization;
import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RepoListFragment extends Fragment {

    private static final String TAG = "RepoListFragment";

    private TextView organizationName, organizationDescription, organizationLocation;
    private ImageView organizationAvatar;
    private RecyclerView repoRecyclerView;
    private RepoAdapter repoAdapter;

    private Organization organizationData;
    private List<Repo> repoList;

    @Inject
    Context mContext;
    @Inject
    GitHubService service;

    public static RepoListFragment newInstance(Organization orgData, List<Repo> repoData) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ORGANIZATION_DATA, orgData);

        //Gets the list of repos to be displayed
        ArrayList<Repo> repoArrayList = new ArrayList<>(repoData.size());
        repoArrayList.addAll(repoData);
        args.putParcelableArrayList(Constants.ARG_REPO_DATA, repoArrayList);

        RepoListFragment fragment = new RepoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        organizationData = getArguments().getParcelable(Constants.ARG_ORGANIZATION_DATA);
        repoList = getArguments().getParcelableArrayList(Constants.ARG_REPO_DATA);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        organizationName = view.findViewById(R.id.fragmentRepoList_organizationNameTextView);
        organizationDescription = view.findViewById(R.id.fragmentRepoList_organizationDescriptionTextView);
        organizationLocation = view.findViewById(R.id.fragmentRepoList_organizationLocationTextView);
        organizationAvatar = view.findViewById(R.id.fragmentRepoList_organizationImageView);
        repoRecyclerView = view.findViewById(R.id.fragmentRepoList_repoRecyclerView);

        getOrganization();
        getRepoList();

        showBackButton();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        ApplicationComponent applicationComponent = getApplicationComponent();
        applicationComponent.injectRepoListFragment(this);
        super.onAttach(context);
    }

    //Displays the data for the organization
    private void getOrganization() {
        organizationName.setText(organizationData.getOrganizationName());
        if(organizationData.getOrganizationDescription() != null && !organizationData.getOrganizationDescription().equals(""))
            organizationDescription.setText(organizationData.getOrganizationDescription());
        else
            organizationDescription.setText(mContext.getString(R.string.no_description_found));
        if(organizationData.getOrganizationLocation() != null && !organizationData.getOrganizationLocation().equals(""))
            organizationLocation.setText(organizationData.getOrganizationLocation());
        else
            organizationLocation.setText(mContext.getString(R.string.no_location_found));
        Glide.with(mContext).load(organizationData.getOrganizationAvatarURL()).into(organizationAvatar);
    }

    //Sets up the recyclerview with the repo data and displays it
    private void getRepoList() {
        repoRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        repoAdapter = new RepoAdapter(repoList, mContext);
        repoRecyclerView.setAdapter(repoAdapter);
    }

    //Gets the Application Component for dagger injection
    private ApplicationComponent getApplicationComponent() {
        return ((GitHubBrowserApplication) getActivity().getApplication()).getApplicationComponent();
    }

    //Displays a back button in the toolbar
    public void showBackButton() {
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
