package com.bigpush.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigpush.R;

public class BigSearchFragment extends BaseFragment {

    public static BigSearchFragment newInstance() {
        BigSearchFragment fragment = new BigSearchFragment();
//        Bundle bdl = new Bundle();
//        bdl.putString("cat", cat);
//        fragment.setArguments(bdl);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_big_search,null);
    }
}
