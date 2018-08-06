package mx.org.bamx.jsh.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import mx.org.bamx.jsh.project.Fragments.ContactDetailFragment;
import mx.org.bamx.jsh.project.Fragments.DetailFragment;
import mx.org.bamx.jsh.project.Fragments.tabsFragment;
import mx.org.bamx.jsh.project.Utils.Utils;

public class DetalleActivty extends AppCompatActivity {

    private String titulo = "Sample text";


    TextView tvTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent myIntent = getIntent();
        titulo = myIntent.getStringExtra(Utils.ARG_TITULO);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        View view = getLayoutInflater().inflate(R.layout.notifications_bar, null);
        ((TextView)view.findViewById(R.id.tv_titulo)).setText(titulo);
        view.findViewById(R.id.btnRegresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //Intent intent = new Intent(NotificationsActivity.this ,MainActivity.class);
                //startActivity(intent);
            }
        });
        Toolbar.LayoutParams layout = new Toolbar.LayoutParams(Toolbar.LayoutParams.FILL_PARENT, Toolbar.LayoutParams.FILL_PARENT);
        getSupportActionBar().setCustomView(view, layout);
        DetailFragment detailFragment = new DetailFragment();
        android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.ly_main, detailFragment);
        ft1.addToBackStack(null);
        ft1.commit();

    }

    @Override
    public void onBackPressed() {this.finish();}
}