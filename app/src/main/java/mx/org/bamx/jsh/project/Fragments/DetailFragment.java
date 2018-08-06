package mx.org.bamx.jsh.project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.org.bamx.jsh.project.AcopioActivity;
import mx.org.bamx.jsh.project.DetailActivity;
import mx.org.bamx.jsh.project.Enumeraciones.AcopioOpciones;
import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Utils;

public class DetailFragment extends Fragment {

    View lySolicitudes,lyDonativos,lyEntradasSalidas;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);
        ((TextView) view.findViewById(R.id.tv_colonia)).setText(Utils.currentItem.cnombre);
        ((TextView) view.findViewById(R.id.tv_domicilio)).setText("Correo: " + Utils.currentItem.cemail);
        ((TextView) view.findViewById(R.id.tv_beneficiarios)).setText("Tel: "+Utils.currentItem.ctelefono + " Ext: "
                        + Utils.currentItem.cextension);

        String s = "";
        String[] myName = Utils.currentItem.cnombre.split(" ");
        for (int a = 0; a < myName.length ; a++) {
            s += myName[a].charAt(0);
            if (a==2)
                break;
        }
        ((TextView) view.findViewById(R.id.tv_iniciales)).setText(s);


        CardView cvContenedor = (CardView) view.findViewById(R.id.cvContenedor);
        cvContenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Utils.ARG_TITULO,"DETALLE DE "+ Utils.currentTab);
                getActivity().startActivity(intent);


            }
        });
        return view;


    }
}