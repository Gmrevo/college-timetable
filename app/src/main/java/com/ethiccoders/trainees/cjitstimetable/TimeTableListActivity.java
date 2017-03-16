package com.ethiccoders.trainees.cjitstimetable;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ethiccoders.trainees.adapters.PeriodCursorAdapter;
import com.ethiccoders.trainees.db.DatabaseManager;

public class TimeTableListActivity extends AppCompatActivity {

    ListView mListView;
    DatabaseManager mDatabasManager = DatabaseManager.getInstance();
    PeriodCursorAdapter churchesCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_list);
        mListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new InitAsyncTask().execute();
    }

    public class InitAsyncTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            return mDatabasManager.getPeriods("Monday");
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            if(null!=cursor) {
                churchesCursorAdapter = new PeriodCursorAdapter(TimeTableListActivity.this, cursor, true);
                mListView.setAdapter(churchesCursorAdapter);
            }
        }
    }

}
