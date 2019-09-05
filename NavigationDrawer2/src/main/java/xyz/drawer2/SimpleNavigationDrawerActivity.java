package xyz.drawer2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by xyz on 2019/9/5.
 * Project Name:AndroidDemos4
 */
public class SimpleNavigationDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_navigation_drawer);

        mDrawer =  findViewById(R.id.drawer);

        mNavigationView =  findViewById(R.id.navigation_view);
        mNavigationView.setItemIconTintList(null);//设置菜单图标恢复本来的颜色
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.subitem_01) {
                    Toast.makeText(SimpleNavigationDrawerActivity.this, "sub item 01", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.subitem_04) {
                    Toast.makeText(SimpleNavigationDrawerActivity.this, "sub item 04", Toast.LENGTH_SHORT).show();

                }
                mDrawer.closeDrawers();
                return true;
            }
        });
    }
}
