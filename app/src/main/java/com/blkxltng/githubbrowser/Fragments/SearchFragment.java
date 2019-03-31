package com.blkxltng.githubbrowser.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blkxltng.githubbrowser.Adapters.RepoAdapter;
import com.blkxltng.githubbrowser.Dagger.Component.ApplicationComponent;
import com.blkxltng.githubbrowser.Dagger.Component.MainActivityComponent;
import com.blkxltng.githubbrowser.Dagger.Qualifier.ApplicationContext;
import com.blkxltng.githubbrowser.GitHubBrowserApplication;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;
import com.blkxltng.githubbrowser.Models.Organization;
import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    public interface SearchFragmentCallback {
        void loadRepoList(Organization organizationData, List<Repo> repoList);
    }

    private static final String TAG = "SearchFragment";
//    private static Context mContext;

    private Button searchButton;
    private EditText organizationEditext;
    private TextView errorTextView;
    private ProgressBar searchProgress;

    private SearchFragmentCallback mCallback;

    private Organization organization;
    private List<Repo> repoList;

    @Inject
    GitHubService service;

    @Inject
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mContext = getApplicationComponent().getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        organizationEditext = view.findViewById(R.id.fragmentSearch_organizationEditText);
        organizationEditext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftKeyboard();
                    searchGitHub(v.getText().toString().toLowerCase());
                    return true;
                }
                return false;
            }
        });

        searchButton = view.findViewById(R.id.fragmentSearch_searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                String searchName = organizationEditext.getText().toString().toLowerCase();

                searchGitHub(searchName);
            }
        });

        errorTextView = view.findViewById(R.id.fragmentSearch_errorTextView);
        searchProgress = view.findViewById(R.id.fragmentSearch_searchProgress);
        return view;
    }

    @Override
    public void onAttach(Context context) {
//        AndroidSupportInjection.inject(this);

        ApplicationComponent applicationComponent = getApplicationComponent();
        applicationComponent.injectSearchFragment(this);
//        mainActivityComponent = DaggerMainActivityComponent.builder()
//                .applicationComponent(applicationComponent)
//                .build();
//
//        mainActivityComponent.injectMainActivity(this);

        super.onAttach(context);
        mCallback = (SearchFragmentCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    private void searchGitHub(final String organizationName) {

        errorTextView.setVisibility(View.INVISIBLE);
        searchProgress.setVisibility(View.VISIBLE);

//        GitHubService service = RetrofitClientInstance.getRetrofitInstance().create(GitHubService.class);
//        GitHubService service = getApplicationComponent().getGitHubService();
        Call<Organization> call = service.getOrganization(organizationName);
        call.enqueue(new Callback<Organization>() {
            @Override
            public void onResponse(Call<Organization> call, Response<Organization> response) {
                if(response.isSuccessful()) {
//                    Log.d(TAG, "onResponse: First repo name is: " + response.body().get(0).getRepoName());
                    organization = response.body();
                    getRepoList(organizationName);
//                    mCallback.loadRepoList(organizationName, response.body());
                } else {
                    errorTextView.setVisibility(View.VISIBLE);
                }
                searchProgress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Organization> call, Throwable t) {
                searchProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(mContext, "Something went wrong (org)...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRepoList(String organizationName) {
        searchProgress.setVisibility(View.VISIBLE);

//        GitHubService service = RetrofitClientInstance.getRetrofitInstance().create(GitHubService.class);
        Call<List<Repo>> call = service.listOrganizationRepos(organizationName);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    repoList = response.body();
                    sortRepoList();
                    if(repoList.size() > 3) {
                        repoList = repoList.subList(0,3);
                    }
                    mCallback.loadRepoList(organization, repoList);
                } else {
                    //do something
                }
                searchProgress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                searchProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(mContext, "Something went wrong (rep)...Please try later!", Toast.LENGTH_SHORT).show();
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

    //Used to hide the software keyboard when the user searches for an organization
    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null)
            inputMethodManager.hideSoftInputFromWindow(organizationEditext.getWindowToken(), 0);
    }

    private ApplicationComponent getApplicationComponent() {
        return ((GitHubBrowserApplication) getActivity().getApplication()).getApplicationComponent();
    }
}
