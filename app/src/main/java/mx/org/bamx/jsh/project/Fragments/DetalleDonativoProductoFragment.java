package mx.org.bamx.jsh.project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.org.bamx.jsh.project.DetailActivity;
import mx.org.bamx.jsh.project.R;
import mx.org.bamx.jsh.project.Utils.Utils;

public class DetalleDonativoProductoFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_donativo_producto, container, false);

        ((TextView) view.findViewById(R.id.tv_producto)).setText(Utils.currentItem.donativoProducto);
        ((TextView) view.findViewById(R.id.tv_peso)).setText(Utils.currentItem.donativoPeso);
        ((TextView) view.findViewById(R.id.tv_fecha)).setText(Utils.currentItem.donativoFechaCaducidad);
        ((TextView) view.findViewById(R.id.tv_cosechado)).setText(Utils.currentItem.donativoCosechado);
        ((TextView) view.findViewById(R.id.tv_pago_cosecha)).setText(Utils.currentItem.donativoPagoCosecha);
        ((TextView) view.findViewById(R.id.tv_embalaje)).setText(Utils.currentItem.donativoEmbalaje);
        ((TextView) view.findViewById(R.id.tv_tipo_embalaje)).setText(Utils.currentItem.donativoTipoEmbalaje);

        return view;
    }
}