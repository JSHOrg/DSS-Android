package mx.org.bamx.jsh.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import mx.org.bamx.jsh.project.Fragments.ContactsFragment;
import mx.org.bamx.jsh.project.Fragments.tabsFragment;
import mx.org.bamx.jsh.project.Utils.Utils;

public class AcopioActivity extends AppCompatActivity {
    mx.org.bamx.jsh.project.Fragments.tabsFragment tabsFragment;
    ContactsFragment contactsFragment;
    TextView tvTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acopio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        View view = getLayoutInflater().inflate(R.layout.notifications_bar, null);
        view.findViewById(R.id.btnRegresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //Intent intent = new Intent(NotificationsActivity.this ,MainActivity.class);
                //startActivity(intent);
            }
        });
        tvTitulo = view.findViewById(R.id.tv_titulo);
        Toolbar.LayoutParams layout = new Toolbar.LayoutParams(Toolbar.LayoutParams.FILL_PARENT, Toolbar.LayoutParams.FILL_PARENT);
        getSupportActionBar().setCustomView(view, layout);

        tabsFragment = new tabsFragment();
        switch (Utils.opcionActualAcopio)
        {
            case Solicitudes:
                contactsFragment = new ContactsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.ly_main_acopio, contactsFragment).commit();
                tvTitulo.setText("Solicitudes de Recoleccion");
                break;
            case Donativos:
                contactsFragment = new ContactsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.ly_main_acopio, contactsFragment).commit();
                tvTitulo.setText("Oferta de donativos");
                break;
            case EntradasYSalidas:
                getSupportFragmentManager().beginTransaction().replace(R.id.ly_main_acopio, tabsFragment).commit();
                tvTitulo.setText("Entradas y Salidas");
                break;
            case Benefactores:
                contactsFragment = new ContactsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.ly_main_acopio, contactsFragment).commit();
                tvTitulo.setText("Benefactores");
                break;
        }

    }

}
