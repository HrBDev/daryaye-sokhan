package ir.ham3da.darya;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import ir.ham3da.darya.databinding.ActivityInfo2Binding;
import ir.ham3da.darya.utility.AppSettings;
import ir.ham3da.darya.utility.SetLanguage;
import ir.ham3da.darya.utility.UtilFunctions;

public class ActivityInfo2 extends AppCompatActivity {


    UtilFunctions UtilFunctions1;
    float textSize;

    ActivityInfo2Binding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInfo2Binding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setSupportActionBar(b.toolbarInfo);

       ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        AppSettings.Init(this);
        textSize = AppSettings.getTextSize();

        UtilFunctions1 = new UtilFunctions(this);
        String title1 = getIntent().getStringExtra("title1");
        String title2 = getIntent().getStringExtra("title2");
        final String text = getIntent().getStringExtra("text");

        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O) {
            b.contentActivityInfo2.text.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        if (!text.isEmpty()) {
            b.contentActivityInfo2.text.setText( UtilFunctions.fromHtml(text), TextView.BufferType.SPANNABLE);
        }

        b.contentActivityInfo2.text.setTextSize(textSize);

        if(title2.isEmpty())
        {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) b.infoAvatar1.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            params.setMargins(0, 0, 0, 0);

            b.title2.setVisibility(View.GONE);

        }
        else {
            b.title2.setText(title2);
        }

        b.fab.setOnClickListener(v -> UtilFunctions1.shareText(text));

        UtilFunctions1.setupToolbarLayout(b.toolbarLayout, false);

        b.toolbarLayout.setTitle(title1);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(SetLanguage.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.slideDown(this); //fire the slide left animation
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info2, menu);
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
}
