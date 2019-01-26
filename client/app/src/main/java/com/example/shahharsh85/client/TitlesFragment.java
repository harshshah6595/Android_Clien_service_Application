package com.example.shahharsh85.client;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by shahharsh85 on 12/2/2017.
 */

public class TitlesFragment extends ListFragment {

    private static final String TAG = "TitlesFragment";
    private ListSelectionListener mListener = null;
    private int mCurrIdx = -1;

    // Callback interface that allows this Fragment to notify the QuoteViewerActivity when
    // user clicks on a List Item
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {

            // Set the ListSelectionListener for communicating with the QuoteViewerActivity
            mListener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set the list adapter for the ListView
        // Discussed in more detail in the user interface classes lesson
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.list_item, QuoteViewerActivity.mTitleArray));

        // If an item has been selected, set its checked state
        if (-1 != mCurrIdx) {
            getListView().setItemChecked(mCurrIdx, true);

            // UB:  10-6-2017 Added this call to handle configuration changes
            // that broke in API 25
            mListener.onListSelection(mCurrIdx);
        }
    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        if (mCurrIdx != pos) {
            mCurrIdx = pos;

            // Inform the QuoteViewerActivity that the item in position pos has been selected
            mListener.onListSelection(pos);
        }
        // Indicates the selected item has been checked
        l.setItemChecked(mCurrIdx, true);
    }
}
