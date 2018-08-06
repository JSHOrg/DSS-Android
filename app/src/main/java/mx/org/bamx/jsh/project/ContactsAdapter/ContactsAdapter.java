package mx.org.bamx.jsh.project.ContactsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import mx.org.bamx.jsh.project.DetailActivity;
import mx.org.bamx.jsh.project.DetalleActivty;
import mx.org.bamx.jsh.project.Utils.Constants;
import mx.org.bamx.jsh.project.Utils.Utils;
import mx.org.bamx.jsh.project.Fragments.MapFragment;
import mx.org.bamx.jsh.project.R;


/**
 * Created by PC on 08/05/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private Activity context;
    private ArrayList<ContactItem> data;
    private LayoutInflater inflater;
    private int previousPosition = 0;

    public  ContactsAdapter(Activity context, ArrayList<ContactItem> data)
    {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contacts_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder myViewHolder, final int position) {

        final MapFragment[] mapFragment = new MapFragment[1];
        myViewHolder.tvBeneficiarios.setLines(1);


        /*myViewHolder.tvColonia.setText(data.get(position).nombre);
        myViewHolder.tvDomicilio.setText(data.get(position).detalle1);
        myViewHolder.tvBeneficiarios.setText(data.get(position).detalle2);

        myViewHolder.tvIniciales.setText(data.get(position).iniciales);
        */


        switch (Utils.opcionActual)
        {
            case Contactos:
            case Mapa:

                myViewHolder.tvColonia.setText(data.get(position).nombre);
                myViewHolder.tvDomicilio.setText(data.get(position).detalle1);
                myViewHolder.tvBeneficiarios.setText(data.get(position).detalle2);
                myViewHolder.tvIniciales.setText(data.get(position).iniciales);

                break;
            case Acopio:

                switch (Utils.opcionActualAcopio)
                {
                    case EntradasYSalidas:

                        myViewHolder.tvColonia.setText(data.get(position).entradaSalidaNombrePro);
                        myViewHolder.tvDomicilio.setText(data.get(position).entradaSalidaFecha);
                        myViewHolder.tvBeneficiarios.setText(data.get(position).entradaSalidaCantidad);
                        myViewHolder.tvIniciales.setText(data.get(position).iniciales);

                        break;
                    case Solicitudes:

                        myViewHolder.tvColonia.setText(data.get(position).solicitudNombreEntrega);
                        myViewHolder.tvDomicilio.setText(data.get(position).detalle1);
                        myViewHolder.tvBeneficiarios.setText(data.get(position).detalle2);
                        myViewHolder.tvIniciales.setText(data.get(position).iniciales);

                        break;
                    case Benefactores:

                        myViewHolder.tvColonia.setText(data.get(position).nombre);
                        myViewHolder.tvDomicilio.setText(data.get(position).detalle1);
                        myViewHolder.tvBeneficiarios.setText(data.get(position).detalle2);
                        myViewHolder.tvIniciales.setText(data.get(position).iniciales);

                        break;
                    case Donativos:
                        myViewHolder.tvColonia.setText(data.get(position).donativoProducto);
                        myViewHolder.tvDomicilio.setText(data.get(position).detalle1);
                        myViewHolder.tvBeneficiarios.setLines(2);
                        myViewHolder.tvBeneficiarios.setText(data.get(position).detalle2);
                        myViewHolder.tvIniciales.setText(data.get(position).iniciales);
                }



                break;
        }

        previousPosition = position;
        switch (Utils.opcionActual)
        {
            case Contactos:
                myViewHolder.cvContenedor.setFocusableInTouchMode(false);
                break;
            case Mapa:
                myViewHolder.cvContenedor.setFocusableInTouchMode(true);
                break;
        }
        myViewHolder.cvContenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.currentItem = data.get(position);
                myViewHolder.cvContenedor.requestFocus();
                Intent intent;
                switch (Utils.opcionActual)
                {
                    case Contactos:

                        if (Utils.currentTab == Constants.HEADER_CENTROS_DE_ACOPIO
                                ||Utils.currentTab == Constants.HEADER_CENTROS_COMUNITARIOS
                                ||Utils.currentTab == Constants.HEADER_BANCOS_DE_ALIMENTOS)
                        {

                            intent = new Intent(context, DetalleActivty.class);
                            intent.putExtra(Utils.ARG_TITULO, data.get(position).nombre);
                            context.startActivity(intent);
                        }
                        else {
                            intent = new Intent(context, DetailActivity.class);
                            intent.putExtra(Utils.ARG_TITULO, "DETALLE DE " + Utils.currentTab);
                            context.startActivity(intent);
                        }
                        break;
                    case Acopio:
                        intent = new Intent(context, DetailActivity.class);

                        switch (Utils.opcionActualAcopio)
                        {
                            case Donativos:
                                intent.putExtra(Utils.ARG_TITULO,"DETALLE DE DONATIVO ");

                                break;
                            case Solicitudes:
                                intent.putExtra(Utils.ARG_TITULO,"DETALLE DE SOLICITUD" );

                                break;
                            case Benefactores:
                                intent.putExtra(Utils.ARG_TITULO,"DETALLE DE BENEFACTOR" );

                                break;
                            case EntradasYSalidas:
                                intent.putExtra(Utils.ARG_TITULO,"DETALLE DE " );
                                intent.putExtra(Utils.ARG_TITULO,"DETALLE DE "+ Utils.currentTab);

                                break;
                        }
                        context.startActivity(intent);

                        break;
                }
            }
        });
        myViewHolder.cvContenedor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.isFocused()) {
                    myViewHolder.tvColonia.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    myViewHolder.tvIniciales.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_green));
                    myViewHolder.chk.setChecked(true);

                    switch (Utils.opcionActual)
                    {
                        case Mapa:

                            mapFragment[0] = MapFragment.newInstance(Double.valueOf(data.get(position).dlatitud)
                                    , Double.valueOf(data.get(position).dlongitud)
                                    ,data.get(position).iniciales);

                            for (Fragment fragment: Utils.fragmentTransaction.getFragments()) {
                                if (fragment == mapFragment[0]) {
                                    Utils.fragmentTransaction.beginTransaction().remove(fragment).commit();
                                }
                            }
                            if (Utils.fragmentTransaction != null)
                                Utils.fragmentTransaction.beginTransaction().add(R.id.lyMap, mapFragment[0]).commit();
                            myViewHolder.cvContenedor.requestFocus();
                            break;

                    }
                }
                else{
                    myViewHolder.tvColonia.setTextColor(context.getResources().getColor(R.color.cardview_dark_background));
                    myViewHolder.tvIniciales.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner));
                    myViewHolder.chk.setChecked(false);}
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvColonia, tvDomicilio, tvIniciales,tvBeneficiarios;
        CheckBox chk;
        CardView cvContenedor;


        public ViewHolder(View itemView) {
            super(itemView);

            tvColonia = (TextView) itemView.findViewById(R.id.tv_colonia);
            tvDomicilio = (TextView) itemView.findViewById(R.id.tv_domicilio);
            tvIniciales = (TextView) itemView.findViewById(R.id.tv_iniciales);
            tvBeneficiarios = (TextView) itemView.findViewById(R.id.tv_beneficiarios);

            cvContenedor = (CardView) itemView.findViewById(R.id.cvContenedor);

            chk = (CheckBox) itemView.findViewById(R.id.chk);
        }
    }

    private void removeItem(ContactItem infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    private void addItem(int position, ContactItem infoData) {

        data.add(position, infoData);
        notifyItemInserted(position);
    }
}
