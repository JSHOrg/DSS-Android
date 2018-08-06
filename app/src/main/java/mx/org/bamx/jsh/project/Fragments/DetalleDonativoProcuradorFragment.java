package mx.org.bamx.jsh.project.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Utils;

public class DetalleDonativoProcuradorFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_donativo_procurados, container, false);
        ((TextView) view.findViewById(R.id.tv_nombre_procurador)).setText(Utils.currentItem.donativoProcurador);
        ((TextView) view.findViewById(R.id.tv_correo_procurador)).setText(Utils.currentItem.donativoProcuradorCorreo);
        ((TextView) view.findViewById(R.id.tv_telefono_procurador)).setText(Utils.currentItem.donativoProcuradorTelefono);
        ((TextView) view.findViewById(R.id.tv_celular_procurador)).setText(Utils.currentItem.donativoProcuradorCelular);

        return view;
    }
}