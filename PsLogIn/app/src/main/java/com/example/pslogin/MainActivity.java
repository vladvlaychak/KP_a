package com.example.pslogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private ArrayAdapter<String> mAdapter;
    private ListView List;
    private  String[] array;
    private ArrayAdapter<String> adapter;
    private  DrawerLayout drawer;
    private  Toolbar toolbar;
    private int category_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List = findViewById(R.id.info_content);
        array = getResources().getStringArray(R.array.spravochnik_info);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,new ArrayList<String>(Arrays.asList(array)) );
        List.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, text_content_activity.class);
                intent.putExtra("category", category_index);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    public void HyperLink(View view) {
        TextView textView =findViewById(R.id.textView);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://mail.google.com/mail/u/2/#inbox?compose=CllgCJfscdnfQSlrRNQVnbwWjHSgKXzmKdTQPWZtTRvWchdQNZjTzVfMptSXWnkwQqGQBMrlWHg'> PsychoLogIn.texnical@gmail.com </a>";
        textView.setText(Html.fromHtml(text));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.id_helper)
        {
            Toast.makeText(this, "Здесь объяснения по терминам психологии", Toast.LENGTH_SHORT).show();
            array = getResources().getStringArray(R.array.spravochnik_info);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.menu_helper);
            category_index = 0;
        }
        else if (id == R.id.id_test)
        {
            Toast.makeText(this, "Здесь множество тестов по психологии", Toast.LENGTH_SHORT).show();
            array = getResources().getStringArray(R.array.all_tests);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.menu_tests);
            category_index = 1;

        }
        else if (id == R.id.id_result)
        {
            Toast.makeText(this, "Тут показанны результаты", Toast.LENGTH_SHORT).show();
            array = getResources().getStringArray(R.array.results_of_tests);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.menu_result);
            category_index = 2;
        }
        else if (id == R.id.id_info)
        {
            Toast.makeText(this, "Основная информация о приложении, его создателях а также  об авторских правах", Toast.LENGTH_SHORT).show();
            array = getResources().getStringArray(R.array.all_about);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.menu_about);
            category_index = 3;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
