package com.application.maje.motionheaderviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.maje.mhvp.IPagerScroll;
import com.maje.mhvp.PagerScrollUtils;

import java.util.ArrayList;
import java.util.List;

public class FragmentListView extends Fragment implements IPagerScroll {

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        mListView = (ListView) view.findViewById(R.id.list_view);
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listData.add("Item" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
        return view;
    }

    @Override
    public boolean isFirstChildOnTop() {
        if (mListView.getChildCount() > 0) {
            return PagerScrollUtils.isFirstChildOnTop(mListView);
        }

        return false;
    }
}
