package com.cs434.courseshare;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class NavigationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_entry, menu);
        return true;
    }
    
    
    public void searchClick(View view) {
    }

    public void profileClick(View view) {
    }
    
    public void recordClick(View view) {
    }
}
