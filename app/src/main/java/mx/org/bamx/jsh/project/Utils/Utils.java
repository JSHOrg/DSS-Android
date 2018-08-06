package mx.org.bamx.jsh.project.Utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import mx.org.bamx.jsh.project.ContactsAdapter.ContactItem;
import mx.org.bamx.jsh.project.Enumeraciones.AcopioOpciones;
import mx.org.bamx.jsh.project.Enumeraciones.MenuOpciones;

/**
 * Created by PC on 30/04/2018.
 */

public  class Utils  {
    public static MenuOpciones opcionActual = MenuOpciones.Contactos,opcionAnterior = null;
    public static AcopioOpciones opcionActualAcopio = null;
    public static String currentTab = "";
    private Activity context;
    private ServiciosWeb.NombreServicioWeb nombreServicioWeb;
    public static ServiciosWeb.NombreServicioWeb nombreServicioWebActual;
    public Utils(Activity context) {
        this.context = context;
    }
    public static String Usuario = null;
    public static double latitud,longitud;
    public static String tiulomap;
    public static ContactItem currentItem;
    public static final String ARG_TITULO = "titulo";


    public static android.support.v4.app.FragmentManager fragmentTransaction = null;
    public static android.support.v4.app.FragmentManager fragmentTransactionDetalle = null;

    public Utils(Activity context, ServiciosWeb.NombreServicioWeb nombreServicioWeb) {
        this.context = context;
        this.nombreServicioWeb = nombreServicioWeb;
    }


    public boolean submitForm(String tipo, TextInputEditText editText, TextInputLayout textInputLayout) {

        switch (tipo) {
            case "Email":
                return validateEmail(editText, textInputLayout);
            case "Contraseña":
                return validatePassword(editText, textInputLayout);
            case "Default":
                return validate(editText, textInputLayout);
        }
        return false;

    }

    private boolean validate(TextInputEditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError("Favor de ingresar usuario");
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    public static Snackbar snackbar(View view, String string, int colorText, int colorBG) {
        Snackbar snackbar = Snackbar.make(view, string, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(colorText);
        snackbarView.setBackgroundColor(colorBG);

        return snackbar;
    }

    private boolean validateEmail(TextInputEditText editText, TextInputLayout textInputLayout) {
        String email = editText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            textInputLayout.setError("Verifique el correo");
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword(TextInputEditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError("Favor de ingresar contraseña");
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
