package com.blkxltng.githubbrowser.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blkxltng.githubbrowser.Adapters.RepoAdapter;
import com.blkxltng.githubbrowser.Constants;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;
import com.blkxltng.githubbrowser.Models.Organization;
import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.Network.RetrofitClientInstance;
import com.blkxltng.githubbrowser.R;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoListFragment extends Fragment {

    private static final String TAG = "RepoListFragment";

    private TextView organizationName, organizationDescription, organizationLocation;
    private ImageView organizationAvatar;
    private RecyclerView repoRecyclerView;
    private RepoAdapter repoAdapter;

    private String organizationId;
    private List<Repo> repoList;
    private Context mContext;

    public static RepoListFragment newInstance(String organizationId) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.ARG_ORGANIZATION_ID, organizationId);

        RepoListFragment fragment = new RepoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        organizationId = getArguments().getString(Constants.ARG_ORGANIZATION_ID);
        Log.d(TAG, "onCreate: organnizationId is " + organizationId);
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

//        repoRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        repoAdapter = new RepoAdapter(repoList, mContext);
//        repoRecyclerView.setAdapter(repoAdapter);

        return view;
    }

    private void getOrganization() {

        GitHubService service = RetrofitClientInstance.getRetrofitInstance().create(GitHubService.class);
        Call<Organization> call = service.getOrganization(organizationId);
        call.enqueue(new Callback<Organization>() {
            @Override
            public void onResponse(Call<Organization> call, Response<Organization> response) {
                if(response.isSuccessful()) {
                    Organization organization = response.body();
                    organizationName.setText(organization.getOrganizationName());
                    if(organization.getOrganizationDescription() != null && !organization.getOrganizationDescription().equals(""))
                        organizationDescription.setText(organization.getOrganizationDescription());
                    else
                        organizationDescription.setText(mContext.getString(R.string.no_description_found));
                    if(organization.getOrganizationLocation() != null && !organization.getOrganizationLocation().equals(""))
                        organizationLocation.setText(organization.getOrganizationLocation());
                    else
                        organizationLocation.setText(mContext.getString(R.string.no_location_found));
                    Glide.with(getContext()).load(organization.getOrganizationAvatarURL()).into(organizationAvatar);
                } else {
                    //insert something here
                }
            }

            @Override
            public void onFailure(Call<Organization> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRepoList() {
//        searchProgress.setVisibility(View.VISIBLE);

        GitHubService service = RetrofitClientInstance.getRetrofitInstance().create(GitHubService.class);
        Call<List<Repo>> call = service.listOrganizationRepos(organizationId);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(response.isSuccessful()) {
//                    Log.d(TAG, "onResponse: First repo name is: " + response.body().get(0).getRepoName());
                    repoList = response.body();
                    sortRepoList();
                    repoRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    repoAdapter = new RepoAdapter(repoList, mContext);
                    repoRecyclerView.setAdapter(repoAdapter);
                } else {
                    //do something
                }
//                searchProgress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                searchProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sortRepoList() {
        Comparator<Repo> starsComparator = new Comparator<Repo>() {
            @Override
            public int compare(Repo o1, Repo o2) {
                return o1.getRepoStarCount().compareTo(o2.getRepoStarCount());
            }
        };

        Collections.sort(repoList, Collections.reverseOrder(starsComparator));
    }
}
