package com.widiyanto.kamusbahasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView tvDetail, tvText;

    public static String Detail = "Detail";
    public static String Text = "Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvText = (TextView)findViewById(R.id.tv_detail_text);
        tvDetail = (TextView)findViewById(R.id.tv_detail_detail);

        String text = getIntent().getStringExtra(Text);
        String detail = getIntent().getStringExtra(Detail);

        tvText.setText(text);
        tvDetail.setText(detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
