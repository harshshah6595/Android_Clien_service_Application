package com.example.shahharsh85.client;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class QuoteViewerActivity extends Activity implements
        TitlesFragment.ListSelectionListener {

    public static List mTitleArray=new ArrayList<String>();;
    public static List mQuoteArray=new ArrayList<String>();;
    private QuotesFragment mDetailsFragment;
    private TitlesFragment mTitlesFragment;


    private static final String TAG = "QuoteViewerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the string arrays with the titles and qutoes


        setContentView(R.layout.activity_quote_viewer);

        // Get a reference to the QuotesFragment
        mDetailsFragment = (QuotesFragment) getFragmentManager().findFragmentById(R.id.details);

        // UB: 10-8-2017 Get a reference to the TitlesFragment
        mTitlesFragment = (TitlesFragment) getFragmentManager().findFragmentById(R.id.titles);
    }

    // Called when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {
        if (mDetailsFragment.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            mDetailsFragment.showQuoteAtIndex(index);
        }
    }
}