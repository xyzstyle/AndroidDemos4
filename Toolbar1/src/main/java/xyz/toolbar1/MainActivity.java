package xyz.toolbar1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar(1);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbar(2);
            }
        });

    }

    private void setToolbar(int id) {

        FrameLayout frameLayout = findViewById(R.id.bar_frame);
        frameLayout.removeAllViews();
        Toolbar toolbar = new Toolbar(this);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        toolbar.setLayoutParams(layoutParams);
        frameLayout.addView(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        switch (id) {
            case 1:
                toolbar.setTitle("abc");
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
                    }
                });


                toolbar.setTitleTextAppearance(this, R.style.ToolbarTitle);
                toolbar.setPopupTheme(R.style.ToolbarPopupTheme);
                toolbar.inflateMenu(R.menu.toolbar);

                toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_search) {
                            Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });

                break;
            case 2:
                toolbar.setTitle("222");

                break;
            default:

        }

    }

}
