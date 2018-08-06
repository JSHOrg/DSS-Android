package mx.org.bamx.jsh.project.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Utils;

public class DetalleDonativoAcopioFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_donativo_acopio, container, false);

        ((TextView) view.findViewById(R.id.tv_fecha_acopio)).setText(Utils.currentItem.donativoFechaAcopio);
        ((TextView) view.findViewById(R.id.tv_domicilio_acopio)).setText(Utils.currentItem.donativoDomicilioAcopio);
        ((TextView) view.findViewById(R.id.tv_telefono_acopio)).setText(Utils.currentItem.donativoTelefono);
        ((TextView) view.findViewById(R.id.tv_extension_acopio)).setText(Utils.currentItem.donativoExtension);
        ((TextView) view.findViewById(R.id.tv_celular_acopio)).setText(Utils.currentItem.donativoCelular);


        return view;
    }
}