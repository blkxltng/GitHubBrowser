package com.blkxltng.githubbrowser.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.blkxltng.githubbrowser.Interfaces.GitHubService;
import com.blkxltng.githubbrowser.MainActivity;
import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.Network.RetrofitClientInstance;
import com.blkxltng.githubbrowser.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    public interface SearchFragmentCallback {
        void loadRepoList(String organization);
    }

    private static final String TAG = "SearchFragment";
    private static Context mContext;

    private Button searchButton;
    private EditText organizationEditext;
    private TextView errorTextView;
    private ProgressBar searchProgress;

    private SearchFragmentCallback mCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();

//        GitHubService service = RetrofitClientInstance.getRetrofitInstance().create(GitHubService.class);
//        Call<List<Repo>> call = service.listOrganizationRepos("google");
//        call.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                Log.d(TAG, "onResponse: First repo name is: " + response.body().get(0).getRepoName());
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
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

        GitHubService service = RetrofitClientInstance.getRetrofitInstance().create(GitHubService.class);
        Call<List<Repo>> call = service.listOrganizationRepos(organizationName);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "onResponse: First repo name is: " + response.body().get(0).getRepoName());
                    mCallback.loadRepoList(organizationName);
                } else {
                    errorTextView.setVisibility(View.VISIBLE);
                }
                searchProgress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                searchProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Used to hide the software keyboard when the user searches for an organization
    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null)
            inputMethodManager.hideSoftInputFromWindow(organizationEditext.getWindowToken(), 0);
    }
}
