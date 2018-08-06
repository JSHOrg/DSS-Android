package mx.org.bamx.jsh.project.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Utils;

public class DetalleDonativoEspecificacionesFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_donativo_especificaciones, container, false);

        ((TextView) view.findViewById(R.id.tv_transporte)).setText(Utils.currentItem.donativoTransporte);
        ((TextView) view.findViewById(R.id.tv_especificaciones)).setText(Utils.currentItem.donativoEspecificaciones);

        return view;
    }
}