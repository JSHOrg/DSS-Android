package mx.org.bamx.jsh.project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.org.bamx.jsh.project.AcopioActivity;
import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Utils;
import mx.org.bamx.jsh.project.Enumeraciones.AcopioOpciones;

/**
 * Created by PC on 03/05/2018.
 */

public class AcopioFragment extends Fragment {

    View lySolicitudes,lyDonativos,lyEntradasSalidas;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_opc_acopio, container, false);


        view.findViewById(R.id.ly_solicitudes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Utils.opcionActualAcopio = AcopioOpciones.Solicitudes;
                Intent intent = new Intent(AcopioFragment.this.getActivity() ,AcopioActivity.class);
                startActivity(intent);

            }
        });

        view.findViewById(R.id.ly_donativos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.opcionActualAcopio = AcopioOpciones.Donativos;
                Intent intent = new Intent(AcopioFragment.this.getActivity() ,AcopioActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.ly_entradas_salidas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.opcionActualAcopio = AcopioOpciones.EntradasYSalidas;
                Intent intent = new Intent(AcopioFragment.this.getActivity() ,AcopioActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.ly_benefactores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.opcionActualAcopio = AcopioOpciones.Benefactores;
                Intent intent = new Intent(AcopioFragment.this.getActivity() ,AcopioActivity.class);
                startActivity(intent);
            }
        });


        return view;


    }
}
