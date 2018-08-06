package mx.org.bamx.jsh.project.Utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.org.bamx.jsh.project.ContactsAdapter.ContactsAdapter;
import mx.org.bamx.jsh.project.ContactsAdapter.ContactsDatos;
import mx.org.bamx.jsh.project.MainActivity;
import mx.org.bamx.jsh.project.NetWorking.Metodos;
import mx.org.bamx.jsh.project.NetWorking.NetServicesJSONObject;
import mx.org.bamx.jsh.project.NetWorking.SetHeaderes;
import mx.org.bamx.jsh.project.NetWorking.onTaskCompleted;
import mx.org.bamx.jsh.project.R;

/**
 * Created by PC on 05/07/2018.
 */

public class ServiciosWeb implements onTaskCompleted {


    JSONArray jsonArray = null;
    private Activity context;
    private NombreServicioWeb nombreServicioWeb = null;
    private NombreServicioWeb nombreServicioWebAnterior = null;
    private RecyclerView recyclerView;
    private View view;
    private RecyclerView.Adapter adapter;



    public enum NombreServicioWeb {
        Login,
        GetSubObject,
        GetBancosAlimentos,
        GetCentrosComunitarios,
        GetCentrosAcopio,
        GetSolicitudesRecoleccion,
        GetOfertasDonativos, GetEntradas, GetSalidas, GetDonadores,GetProveedores,
    }

    public ServiciosWeb(NombreServicioWeb nombreServicioWeb ) {
        this.nombreServicioWeb = nombreServicioWeb;
    }


    //-----------------Basico-------------//
    public ServiciosWeb(Activity context, NombreServicioWeb nombreServicioWeb) {

        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
        Utils.nombreServicioWebActual = nombreServicioWeb;

    }
    //-----------------simple-------------//
    public ServiciosWeb(Activity context, View view, NombreServicioWeb nombreServicioWeb) {
        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
        this.view = view;
        Utils.nombreServicioWebActual = nombreServicioWeb;
    }

    //-----------------Para Usarlo Con recycler--------------//
    public ServiciosWeb(Activity context, NombreServicioWeb nombreServicioWeb,
                        RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        Utils.nombreServicioWebActual = nombreServicioWeb;
    }

    public void LogIn(String txtUsuario, String txtContrasena) {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.add("grant_type");
        Constants.KEY_NAME.add("username");
        Constants.KEY_NAME.add("password");
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Verificando Credenciales").execute("post", Metodos.oauth + "/token","password" , txtUsuario, txtContrasena);
    }

    public void GetBancosAlimentos()
    {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.bancoAlimentos);

    }

    public void GetCentrosComunitarios()
    {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.comunitarios);
    }

    public void GetCentrosAcopio()
    {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.centrosAcopio);
    }

    public void GetDonadores()
    {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.donadores);
    }

    public void GetSolicitudesRecoleccion() {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.solicitudesrecoleccion);

    }


    public void GetOfertasDonativos() {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.detalledonativo);

    }


    public void GetEntradas() {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.entradasalmacen);

    }


    public void GetSalidas() {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.salidasalmacen);

    }

    public void GetProveedores() {
        Constants.KEY_NAME = new ArrayList<>();
        Constants.KEY_NAME.clear();
        new NetServicesJSONObject(ServiciosWeb.this, context, true, "Espere un Momento").execute("get", Metodos.api + "/" + Metodos.proveedores);

    }

    @Override
    public void onTaskCompleted(String response) {
        if (response != null) {
            try {
                if (nombreServicioWeb != nombreServicioWebAnterior)
                switch (nombreServicioWeb) {
                    case Login:
                        if (new JSONObject(response).isNull("error")) {
                            if (SetHeaderes.TokenServicios == null) {
                                SetHeaderes.TokenServicios = new JSONObject(response).getString("access_token");
                            }

                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                            Toast.makeText(context, "Inicio de sesión realizado con éxito", Toast.LENGTH_SHORT).show();

                        } else {

                            if (new JSONObject(response).getString("error_description") == "Bad credentials")
                            Utils.snackbar(view,  "Datos no válidos" ,
                                    context.getResources().getColor(
                                            R.color.colorAccent),
                                    context.getResources().getColor(R.color.colorPrimary)).show();
                            else
                                Utils.snackbar(view, "Error al obtener los datos",
                                        context.getResources().getColor(
                                                R.color.colorAccent),
                                        context.getResources().getColor(R.color.colorPrimary)).show();

                        }
                        break;

                    case GetSolicitudesRecoleccion:
                    case GetSalidas:
                    case GetEntradas:
                    case GetOfertasDonativos:
                    case GetCentrosAcopio:
                    case GetDonadores:
                    case GetCentrosComunitarios:
                    case GetBancosAlimentos:
                    case GetProveedores:
                        if (new JSONObject(response).isNull("error")) {

                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
                            recyclerView.setLayoutManager(mLayoutManager);
                            adapter = new ContactsAdapter(context, ContactsDatos.Data(response,nombreServicioWeb));
                            recyclerView.setAdapter(adapter);

                        } else {

                            Utils.snackbar(view,  new JSONObject(response).getString("error_description") ,
                                    context.getResources().getColor(R.color.colorAccent),
                                    context.getResources().getColor(R.color.colorPrimary)).show();

                        }

                        break;
                    case GetSubObject:
                        if (new JSONObject(response).isNull("error")) {

                            ContactsDatos.jsonArraySub = new JSONArray(response);
                            recyclerView.setAdapter(adapter);
                        }

                        break;
                }
            } catch (Exception ex) {
                Utils.snackbar(view,"Error al obtener los datos" ,
                        context.getResources().getColor(
                                R.color.colorAccent),
                        context.getResources().getColor(R.color.colorPrimary)).show();
                Log.e("ErrorAlIngresar", ex.toString());
            }
        }
    }
}