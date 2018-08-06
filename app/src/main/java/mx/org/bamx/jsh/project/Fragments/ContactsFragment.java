package mx.org.bamx.jsh.project.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Constants;
import mx.org.bamx.jsh.project.Utils.ServiciosWeb;
import mx.org.bamx.jsh.project.Utils.Utils;
import mx.org.bamx.jsh.project.ContactsAdapter.ContactsAdapter;


public class ContactsFragment extends Fragment {

    private ServiciosWeb.NombreServicioWeb servicioWeb;
    View container;
    RecyclerView lyRecycler;
    ContactsAdapter contactsAdapter;
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(), 1);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        this.container = container;
        lyRecycler = (RecyclerView) view.findViewById(R.id.ly_recycler);
        //lyRecycler.setAdapter(contactsAdapter);
        // Inflate the layout for this fragment



        switch (Utils.opcionActual) {
            case Inicio:
                break;
            case Contactos:
                setContacts();
                break;
            case Mapa:
                setMaps();
                break;
            case Acopio:
                setAcopio();
            default:
                break;
        }
        return view;
    }

    MyReceiver r;
    public void refresh() {
        int currentPage = ((ViewPager)container).getCurrentItem();
        //int pos= ((ViewPager)container).getAdapter().getItemPosition(this.getId());
        Utils.currentTab =((ViewPager)container).getAdapter().getPageTitle(currentPage).toString();

        switch (Utils.opcionActual) {
            case Inicio:
                break;
            case Mapa:
                setMaps();
                break;
            case Contactos:
                setContacts();
                break;
            case Acopio:
                setAcopio();
            default:
                break;
        }
        Log.i("Refresh", "YES");
    }

    public void setContacts()
    {
        int currentPage = ((ViewPager)container).getCurrentItem();
        //int pos= ((ViewPager)container).getAdapter().getItemPosition(this.getId());
        Utils.currentTab =((ViewPager)container).getAdapter().getPageTitle(currentPage).toString();
        if(Utils.currentTab == Constants.HEADER_BANCOS_DE_ALIMENTOS)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetBancosAlimentos ,lyRecycler,contactsAdapter).GetBancosAlimentos();
        else if (Utils.currentTab == Constants.HEADER_CENTROS_COMUNITARIOS)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetCentrosComunitarios ,lyRecycler,contactsAdapter).GetCentrosComunitarios();
        else if(Utils.currentTab == Constants.HEADER_CENTROS_DE_ACOPIO)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetCentrosAcopio ,lyRecycler,contactsAdapter).GetCentrosAcopio();
        else if (Utils.currentTab == Constants.HEADER_BENEFACTORES)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetDonadores ,lyRecycler,contactsAdapter).GetDonadores();
        else if (Utils.currentTab == Constants.HEADER_PROVEDOREES)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetProveedores ,lyRecycler,contactsAdapter).GetProveedores();


    }

    public void setMaps()
    {
        int currentPage = ((ViewPager)container).getCurrentItem();
        //int pos= ((ViewPager)container).getAdapter().getItemPosition(this.getId());
        Utils.currentTab =((ViewPager)container).getAdapter().getPageTitle(currentPage).toString();
        if(Utils.currentTab == Constants.HEADER_BANCOS_DE_ALIMENTOS)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetBancosAlimentos ,lyRecycler,contactsAdapter).GetBancosAlimentos();
        else if (Utils.currentTab == Constants.HEADER_CENTROS_COMUNITARIOS)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetCentrosComunitarios ,lyRecycler,contactsAdapter).GetCentrosComunitarios();
        else if(Utils.currentTab == Constants.HEADER_CENTROS_DE_ACOPIO)
            new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetBancosAlimentos ,lyRecycler,contactsAdapter).GetBancosAlimentos();
    }

    public void setAcopio()
    {
        switch (Utils.opcionActualAcopio)
        {
            case Solicitudes:
                new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetSolicitudesRecoleccion ,lyRecycler,contactsAdapter).GetSolicitudesRecoleccion();
                break;
            case Donativos:
                new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetOfertasDonativos ,lyRecycler,contactsAdapter).GetOfertasDonativos();
                break;
            case EntradasYSalidas:
                int currentPage = ((ViewPager)container).getCurrentItem();
                //int pos= ((ViewPager)container).getAdapter().getItemPosition(this.getId());
                Utils.currentTab =((ViewPager)container).getAdapter().getPageTitle(currentPage).toString();

                if(Utils.currentTab == Constants.HEADER_ENTRADAS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetEntradas,lyRecycler,contactsAdapter).GetEntradas();
                else if (Utils.currentTab == Constants.HEADER_SALIDAS)
                    new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetSalidas,lyRecycler,contactsAdapter).GetSalidas();

                break;
            case Benefactores:
                new ServiciosWeb(getActivity(), ServiciosWeb.NombreServicioWeb.GetDonadores ,lyRecycler,contactsAdapter).GetDonadores();
                break;
        }


    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(r);
    }

    public void onResume() {
        super.onResume();
        r = new MyReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(r,
                new IntentFilter("TAG_REFRESH"));
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ContactsFragment.this.refresh();
        }
    }

}
