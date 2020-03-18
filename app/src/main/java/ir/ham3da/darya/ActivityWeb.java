package ir.ham3da.darya;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import ir.ham3da.darya.databinding.ActivityWebBinding;
import ir.ham3da.darya.utility.SetLanguage;

public class ActivityWeb extends AppCompatActivity {

    ActivityWebBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            SetLanguage.wrap(this);
        }
        b = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String title1 = getIntent().getStringExtra("title");
        String text = getIntent().getStringExtra("text");
        setTitle(title1);
        b.webView2.loadDataWithBaseURL("file:///android_res/", text, "text/html", "UTF-8", null);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SetLanguage.wrap(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.slideDown(this); //fire the slide left animation
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            Bungee.slideDown(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(SetLanguage.wrap(newBase));
    }
}
