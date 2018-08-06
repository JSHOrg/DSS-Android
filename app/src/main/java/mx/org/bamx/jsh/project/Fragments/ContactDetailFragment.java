package mx.org.bamx.jsh.project.Fragments;

/**
 * Created by PC on 11/07/2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Constants;
import mx.org.bamx.jsh.project.Utils.ServiciosWeb;
import mx.org.bamx.jsh.project.Utils.Utils;


public class ContactDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_nombre = "nombre";
    private static final String ARG_banco = "banco";
    private static final String ARG_apellido = "apellido";
    private static final String ARG_telefono = "telefono";
    private static final String ARG_extension = "extension";
    private static final String ARG_celular = "celular";
    private static final String ARG_correo = "correo";
    private static final String ARG_tipo = "tipo";
    // TODO: Rename and change types of parameters

    private String nombre;
    private String apellido;
    private String telefono;
    private String extension;
    private String celular;
    private String correo;
    private String tipo;
    private String banco;

    //Contactos
    private TextView  tvTipo,tvBanco,
            tvNombre,tvApellido,tvTelefono,tvExtension,tvCelular,tvCorreo;

    //DETALLE SOLICITUD
    private TextView tvEmpresa, tvDomicilio,tvCiudad,tvNombreContacto,tvProducto,
            tvCantidad,tvFecha,tvHora,tvTransporte,tvNombreEntrega,tvNombreRecibe;

    public ContactDetailFragment() {
        // Required empty public constructor
    }
    View view = null;
    LayoutInflater inflate = null;
    ViewGroup container;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ContactDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetailFragment newInstance( String nombre, String banco
            , String apellido, String telefono, String extension, String celular, String correo
            , String tipo) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_nombre, nombre);
        args.putString(ARG_apellido, apellido);
        args.putString(ARG_telefono, telefono);
        args.putString(ARG_extension, extension);
        args.putString(ARG_celular, celular);
        args.putString(ARG_correo, correo);
        args.putString(ARG_tipo, tipo);
        args.putString(ARG_banco, banco);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            tipo = getArguments().getString(ARG_tipo);
            nombre = getArguments().getString(ARG_nombre);
            apellido = getArguments().getString(ARG_apellido);
            telefono = getArguments().getString(ARG_telefono);
            extension = getArguments().getString(ARG_extension);
            celular = getArguments().getString(ARG_celular);
            correo = getArguments().getString(ARG_correo);
            banco = getArguments().getString(ARG_banco);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflate = inflater;
        this.container = container;
        switch (Utils.opcionActual)
        {
            case Contactos:

                if (Utils.currentTab == Constants.HEADER_BENEFACTORES)
                    cargarBenefactores();
                else {
                    view = inflater.inflate(R.layout.fragment_detalle_contacto, container, false);
                    tvTipo = (TextView) view.findViewById(R.id.tv_tipo);
                    tvBanco = (TextView) view.findViewById(R.id.tv_banco);
                    tvNombre = (TextView) view.findViewById(R.id.tv_nombre);
                    tvApellido = (TextView) view.findViewById(R.id.tv_apellido);
                    tvTelefono = (TextView) view.findViewById(R.id.tv_telefono);
                    tvExtension = (TextView) view.findViewById(R.id.tv_extension);
                    tvCelular = (TextView) view.findViewById(R.id.tv_celular);
                    tvCorreo = (TextView) view.findViewById(R.id.tv_correo);

                    tvTipo.setText(Utils.currentTab.replaceAll("ES","").replaceAll("S",""));
                    tvBanco.setText(Utils.currentItem.nombre);
                    tvNombre.setText(Utils.currentItem.cnombre);
                    tvApellido.setText(Utils.currentItem.capellido);
                    tvTelefono.setText(Utils.currentItem.ctelefono);
                    tvExtension.setText(Utils.currentItem.cextension);
                    tvCelular.setText(Utils.currentItem.ccelular);
                    tvCorreo.setText(Utils.currentItem.cemail);}

                break;
            case Acopio:
                switch (Utils.opcionActualAcopio)
                {
                    case Donativos:
                        break;
                    case Solicitudes:
                        view = inflater.inflate(R.layout.fragment_detalle_solicitud, container, false);

                        ((TextView) view.findViewById(R.id.tv_empresa)).setText(Utils.currentItem.solicitudEmpresa);
                        ((TextView) view.findViewById(R.id.tv_domicilio)).setText(Utils.currentItem.solicitudDomicilio);
                        ((TextView) view.findViewById(R.id.tv_ciudad)).setText(Utils.currentItem.solicitudCiudad);
                        ((TextView) view.findViewById(R.id.tv_nombre_contacto)).setText(Utils.currentItem.solicitudContacto);
                        ((TextView) view.findViewById(R.id.tv_producto)).setText(Utils.currentItem.solicitudProducto);
                        ((TextView) view.findViewById(R.id.tv_cantidad)).setText(Utils.currentItem.solicitudCantidad);
                        ((TextView) view.findViewById(R.id.tv_fecha)).setText(Utils.currentItem.solicitudFechaR);
                        ((TextView) view.findViewById(R.id.tv_hora)).setText(Utils.currentItem.solicitudHoraR);
                        ((TextView) view.findViewById(R.id.tv_transporte)).setText(Utils.currentItem.solicitudTransporte);
                        ((TextView) view.findViewById(R.id.tv_nombre_entrega)).setText(Utils.currentItem.solicitudNombreEntrega);
                        ((TextView) view.findViewById(R.id.tv_nombre_recibe)).setText(Utils.currentItem.aolicitudNombreRecibe);



                        break;
                    case Benefactores:

                        cargarBenefactores();

                        break;
                    case EntradasYSalidas:

                        if(Utils.currentTab == Constants.HEADER_ENTRADAS) {
                            view = inflater.inflate(R.layout.fragment_detalle_entrada, container, false);

                            ((TextView) view.findViewById(R.id.tv_nombre)).setText(Utils.currentItem.entradaSalidaNombreC);
                            ((TextView) view.findViewById(R.id.tv_direccion)).setText(Utils.currentItem.entradaSalidaDireccionC);
                            ((TextView) view.findViewById(R.id.tv_telefono)).setText(Utils.currentItem.entradaSalidaTelefonoC);
                            ((TextView) view.findViewById(R.id.tv_fecha)).setText(Utils.currentItem.entradaSalidaFecha);
                            ((TextView) view.findViewById(R.id.tv_producto)).setText(Utils.currentItem.entradaSalidaNombrePro);
                            ((TextView) view.findViewById(R.id.tv_cantidad)).setText(Utils.currentItem.entradaSalidaCantidad);
                            ((TextView) view.findViewById(R.id.tv_unidad)).setText(Utils.currentItem.entradaSalidaUnidad);
                            ((TextView) view.findViewById(R.id.tv_descripción)).setText(Utils.currentItem.entradaSalidaDescripcion);
                            ((TextView) view.findViewById(R.id.tv_peso_unitario)).setText(Utils.currentItem.entradaSalidaPesoU);
                            ((TextView) view.findViewById(R.id.tv_peso_total)).setText(Utils.currentItem.entradaSalidaPesoT);


                        }
                        else if (Utils.currentTab == Constants.HEADER_SALIDAS) {
                            view = inflater.inflate(R.layout.fragment_detalle_salida, container, false);

                            ((TextView) view.findViewById(R.id.tv_nombre)).setText(Utils.currentItem.entradaSalidaNombrePro);
                            ((TextView) view.findViewById(R.id.tv_motivo)).setText(Utils.currentItem.entradaSalidaDescripcion);
                            ((TextView) view.findViewById(R.id.tv_cantidad)).setText(Utils.currentItem.entradaSalidaCantidad);
                            ((TextView) view.findViewById(R.id.tv_salida)).setText(Utils.currentItem.entradaSalidaFecha);
                        }
                            break;
                }
                break;
        }
        // Inflate the layout for this fragment


        return view;
    }

    private void cargarBenefactores()
    {
        view = this.inflate.inflate(R.layout.fragment_detalle_benefactor, container, false);

        ((TextView) view.findViewById(R.id.tv_razon)).setText(Utils.currentItem.razonSocial);
        ((TextView) view.findViewById(R.id.tv_rfc)).setText(Utils.currentItem.rfc);
        ((TextView) view.findViewById(R.id.tv_telefono)).setText(Utils.currentItem.telefono);
        ((TextView) view.findViewById(R.id.tv_correo)).setText(Utils.currentItem.email);
        ((TextView) view.findViewById(R.id.tv_calificacion)).setText(String.valueOf(Utils.currentItem.calificacion));
        ((TextView) view.findViewById(R.id.tv_fecha)).setText(Utils.currentItem.fechaRegistro);
        ((TextView) view.findViewById(R.id.tv_comentarios)).setText(Utils.currentItem.comentario);
    }

}