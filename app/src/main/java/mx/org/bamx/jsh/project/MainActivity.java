package mx.org.bamx.jsh.project;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import mx.org.bamx.jsh.project.Fragments.AcopioFragment;
import mx.org.bamx.jsh.project.Fragments.tabsFragment;
import mx.org.bamx.jsh.project.Utils.Utils;
import mx.org.bamx.jsh.project.Enumeraciones.MenuOpciones;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View ly_back,lyMap, view ;
    mx.org.bamx.jsh.project.Fragments.tabsFragment tabsFragment;
    AcopioFragment acopioFragment;
    TextView tvTitulo;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        view = getLayoutInflater().inflate(R.layout.bar, null);
        tvTitulo = view.findViewById(R.id.tvTitulo);


        Toolbar.LayoutParams layout = new Toolbar.LayoutParams(Toolbar.LayoutParams.FILL_PARENT, Toolbar.LayoutParams.FILL_PARENT);
        getSupportActionBar().setCustomView(view, layout);


        lyMap = (FrameLayout) findViewById(R.id.lyMap);
        lyMap.setVisibility(View.GONE);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);

        ly_back = (View) headerview.findViewById(R.id.navHeader);
        TextView tvUsuario = (TextView) ly_back.findViewById(R.id.tv_usuario);
        tvUsuario.setText(Utils.Usuario);

        btnBack = (ImageView) headerview.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Utils.opcionActual = MenuOpciones.Contactos;
         switch (Utils.opcionActual)
        {
            case Mapa:
                navigationView.setCheckedItem(R.id.nav_mapa);
                setMaps();
                break;
            case Contactos:
                navigationView.setCheckedItem(R.id.nav_contactos);
                setContacts();
                break;
            case Acopio:
                navigationView.setCheckedItem(R.id.nav_acopio);
                setAcopio();
                break;
        }

        //setFragment(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_salir) {
            new SalirDialog().show(getSupportFragmentManager(), "SimpleDialog");

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        for (Fragment fragment:getSupportFragmentManager().getFragments()) {
            if (fragment!=null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }


        tabsFragment = new tabsFragment();

        if (id == R.id.nav_contactos) {

            setContacts();


        } else if (id == R.id.nav_mapa) {

            setMaps();

        } else if (id == R.id.nav_acopio) {

            setAcopio();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setContacts()
    {

        lyMap.setVisibility(View.GONE);
        tabsFragment = new tabsFragment();
        Utils.opcionActual= MenuOpciones.Contactos;
        android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.lyMain, tabsFragment);
        ft1.addToBackStack(null);
        ft1.commit();
        tvTitulo.setText("Contactos");
    }

    public void setMaps()
    {

        lyMap.setVisibility(View.VISIBLE);
        tabsFragment = new tabsFragment();
        Utils.opcionActual= MenuOpciones.Mapa;
        Utils.fragmentTransaction = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.lyMain, tabsFragment);
        ft1.addToBackStack(null);
        ft1.commit();

        tvTitulo.setText("Mapa");
    }

    public void setAcopio()
    {
        lyMap.setVisibility(View.GONE);
        Utils.opcionActual= MenuOpciones.Acopio;
        acopioFragment= new AcopioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.lyMain, acopioFragment).commit();
        tvTitulo.setText("Acopio");

    }


}
