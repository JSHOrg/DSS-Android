package mx.org.bamx.jsh.project.ContactsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import mx.org.bamx.jsh.project.Utils.ServiciosWeb;

import mx.org.bamx.jsh.project.Utils.Utils;
import mx.org.bamx.jsh.project.NetWorking.RequestGet;


/**
 * Created by PC on 08/05/2018.
 */

public class ContactsDatos {

    public static JSONArray jsonArraySub = null;
    public static JSONObject jsonObject = null;


    public static ArrayList<ContactItem> Data(String response, ServiciosWeb.NombreServicioWeb servicioWeb)
    {
        JSONArray jsonArray = null;

        ArrayList<ContactItem> ContactsItems = new ArrayList<>();
        int[] ID = {1,2, 3,4,5, 6,7};
        DateFormat iFormatter = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat oFormatter = new SimpleDateFormat("dd/MM/yyyy");

        switch (Utils.nombreServicioWebActual){
            case GetBancosAlimentos:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("bancoalimentos"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;


                        //current.id = ID[i];
                        current.nombre = jsonArray.getJSONObject(i).getString("nombre");
                        current.telefono = jsonArray.getJSONObject(i).getString("telefono");
                        current.email = jsonArray.getJSONObject(i).getString("email");
                        current.razonSocial = jsonArray.getJSONObject(i).getString("razonSocial");
                        current.calificacion = jsonArray.getJSONObject(i).getInt("calificacion");
                        current.fechaRegistro = jsonArray.getJSONObject(i).getString("fechaRegistro");


                        current.cnombre = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("nombre");
                        current.capellido = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("apellido");
                        current.ctelefono = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("telefono");
                        current.ccelular = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("celular");
                        current.cemail = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("email");
                        current.cextension = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("extension");


                        current.dcalle = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("numero");
                        current.dciudad = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("ciudad");
                        current.destado = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("estado");
                        current.dlongitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("longitud");
                        current.dlatitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("latitud");
                        current.dcolonia = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("colonia");
                        current.dcp = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("cp");

                        current.detalle1 = "C.P "+ current.dcp + " " + current.dciudad + ", "+ current.destado;
                        current.detalle2 = current.dcalle + " No. " + current.dnumero + " Col. " + current.dcolonia;
                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case GetCentrosComunitarios:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("comunitarios"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;


                        //current.id = ID[i];
                        current.nombre = jsonArray.getJSONObject(i).getString("nombre");
                        //current.telefono = jsonArray.getJSONObject(i).getString("telefono");
                        //current.email = jsonArray.getJSONObject(i).getString("email");
                        //current.razonSocial = jsonArray.getJSONObject(i).getString("razonSocial");
                        //current.calificacion = jsonArray.getJSONObject(i).getInt("calificacion");
                        current.fechaRegistro = jsonArray.getJSONObject(i).getString("fechaRegistro");



                        current.cnombre = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("nombre");
                        current.capellido = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("apellido");
                        current.ctelefono = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("telefono");
                        current.ccelular = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("celular");
                        current.cemail = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("email");
                        current.cextension = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("extension");

                        current.dcalle = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("numero");
                        current.dciudad = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("ciudad");
                        current.destado = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("estado");
                        current.dlongitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("longitud");
                        current.dlatitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("latitud");
                        current.dcolonia = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("colonia");
                        current.dcp = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("cp");

                        current.detalle1 = "C.P "+ current.dcp + " " + current.dciudad + ", "+ current.destado;
                        current.detalle2 = current.dcalle + " No. " + current.dnumero + " Col. " + current.dcolonia;

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case GetCentrosAcopio:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("almacenes"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;


                        //current.id = ID[i];
                        current.nombre = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("nombre");
                        current.telefono = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("telefono");
                        current.email = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("email");
                        current.razonSocial = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("razonSocial");
                        current.calificacion = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getInt("calificacion");
                        current.fechaRegistro = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("fechaRegistro");



                        current.cnombre = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("nombre");
                        current.capellido = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("apellido");
                        current.ctelefono = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("telefono");
                        current.ccelular = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("celular");
                        current.cemail = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("email");
                        current.cextension = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("extension");

                        current.dcalle = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("numero");
                        current.dciudad = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("ciudad");
                        current.destado = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("estado");
                        current.dlongitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("longitud");
                        current.dlatitud = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("latitud");
                        current.dcolonia = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("colonia");
                        current.dcp = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("cp");

                        current.detalle1 = "C.P "+ current.dcp + " " + current.dciudad + ", "+ current.destado;
                        current.detalle2 = current.dcalle + " No. " + current.dnumero + " Col. " + current.dcolonia;

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case GetDonadores:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("donadores"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();



                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;


                        //current.id = ID[i];
                        current.nombre = jsonArray.getJSONObject(i).getString("razonSocial");
                        current.telefono = jsonArray.getJSONObject(i).getString("telefono");
                        current.email = jsonArray.getJSONObject(i).getString("email");
                        current.razonSocial = jsonArray.getJSONObject(i).getString("razonSocial");
                        current.calificacion = jsonArray.getJSONObject(i).getInt("calificacion");

                        current.rfc = jsonArray.getJSONObject(i).getString("rfc");
                        String myDate= jsonArray.getJSONObject(i).getString("fechaRegistro");
                        current.comentario = jsonArray.getJSONObject(i).getString("comentarios");

                        String strDateTime="";
                        try {
                            strDateTime = oFormatter.format(iFormatter.parse(myDate));

                        } catch (ParseException ex) {
                            ex.printStackTrace();

                        }

                        current.fechaRegistro = strDateTime;



                        current.cnombre = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("nombre");
                        current.capellido = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("razonSocial");
                        current.ctelefono = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("telefono");
                        //current.ccelular = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("celular");
                        current.cemail = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("email");
                        //current.cextension = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getString("extension");

                        current.dcalle = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("numero");
                        current.dciudad = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("ciudad");
                        current.destado = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("estado");
                        current.dlongitud = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("longitud");
                        current.dlatitud = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("latitud");
                        current.dcolonia = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("colonia");
                        current.dcp = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("cp");

                        current.detalle1 = "Correo: "+ current.cemail;
                        current.detalle2 = "Registrado: " + current.fechaRegistro;

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;


            case GetSolicitudesRecoleccion:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("solicitudesrecoleccion"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        jsonObject = new JSONObject(new RequestGet().requestGet(jsonArray.getJSONObject(i).getJSONObject("_links")
                                .getJSONObject("detalleDonativo").getString("href").replace("{?projection}","")));

                        if(jsonObject!= null) {
                        }

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getJSONObject("proveedor").getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;

                        current.solicitudProducto = jsonObject.getJSONObject("_embedded").getJSONObject("producto").getString("nombre");

                        //current.id = ID[i];
                        current.solicitudNombreEntrega = jsonArray.getJSONObject(i).getJSONObject("proveedor").getString("nombre");
                        current.solicitudDomicilio = jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("bancoAlimentosOfertante")
                                .getJSONObject("direccion").getString("calle") + " " + jsonObject.getJSONObject("_embedded").getJSONObject("donativo")
                                .getJSONObject("bancoAlimentosOfertante").getJSONObject("direccion").getString("numero") ;
                        current.solicitudCiudad = jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("bancoAlimentosOfertante")
                                .getJSONObject("direccion").getString("ciudad") + ", " + jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("bancoAlimentosOfertante")
                                .getJSONObject("direccion").getString("estado");
                        current.solicitudContacto = jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("contactoDonador")
                                .getString("nombre");
                        current.solicitudCantidad = jsonObject.getString("cantidadKg") +"Kg";


                        String myDate= jsonArray.getJSONObject(i).getString("fechaProgramada").substring(0,10);
                        String strDateTime="";
                        try {
                            strDateTime = oFormatter.format(iFormatter.parse(myDate));

                        } catch (ParseException ex) {
                            ex.printStackTrace();

                        }


                        current.solicitudFechaR = strDateTime;
                        current.solicitudHoraR= jsonArray.getJSONObject(i).getString("fechaProgramada").substring(12,16);
                        current.solicitudTransporte= jsonObject.getJSONObject("_embedded").getJSONObject("donativo")
                                .getJSONObject("transportesUnidades").getString("tipoUnidad");
                        current.solicitudEmpresa = current.solicitudNombreEntrega ;
                        current.aolicitudNombreRecibe = jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("bancoAlimentosPropietario")
                                .getString("nombre");


                        current.detalle1 = "Fecha recolección: "+ current.solicitudFechaR;
                        current.detalle2 =  "Producto: "+ current.solicitudProducto;

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case GetProveedores:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("proveedores"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;


                        //current.id = ID[i];
                        current.nombre = jsonArray.getJSONObject(i).getString("nombre");
                        //current.telefono = jsonArray.getJSONObject(i).getString("telefono");
                        //current.email = jsonArray.getJSONObject(i).getString("email");
                        //current.razonSocial = jsonArray.getJSONObject(i).getString("razonSocial");
                        //current.calificacion = jsonArray.getJSONObject(i).getInt("calificacion");
                        current.fechaRegistro = jsonArray.getJSONObject(i).getString("fechaRegistro");



                        current.cnombre = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("nombre");
                        current.capellido = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("apellido");
                        current.ctelefono = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("telefono");
                        current.ccelular = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("celular");
                        current.cemail = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("email");
                        current.cextension = jsonArray.getJSONObject(i).getJSONObject("contacto").getString("extension");

                        /* current.dcalle = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("numero");
                        current.dciudad = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("ciudad");
                        current.destado = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("estado");
                        current.dlongitud = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("longitud");
                        current.dlatitud = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("latitud");
                        current.dcolonia = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("colonia");
                        current.dcp = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("cp");
                        */
                        current.detalle1 = "Correo: "+ current.cemail;
                        current.detalle2 =  "Tel.: "+ current.ctelefono;

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;


            case GetEntradas:

                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("entradasalmacen"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {


                        jsonObject = new JSONObject(new RequestGet().requestGet(jsonArray.getJSONObject(i).getJSONObject("_links")
                                .getJSONObject("detalleDonativo").getString("href").replace("{?projection}","")));


                        ContactItem current = new ContactItem();
                        jsonArray.getJSONObject(i).getJSONObject("_links").getJSONObject("detalleDonativo").getString("href");

                        String myDate= jsonArray.getJSONObject(i).getString("fechaEntrada").substring(0,10);
                        String strDateTime="";
                        try {
                            strDateTime = oFormatter.format(iFormatter.parse(myDate));

                        } catch (ParseException ex) {
                            ex.printStackTrace();

                        }
                        current.entradaSalidaFecha = "Fecha: " +  strDateTime;

                        current.dcalle = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("calle");
                        current.dnumero = jsonArray.getJSONObject(i).getJSONObject("bancoAlimentos").getJSONObject("direccion").getString("numero");
                        current.detalle2 = current.dcalle + " No. " + current.dnumero;


                        current.entradaSalidaCantidad = "Cantidad KG: " + jsonArray.getJSONObject(i).getJSONObject("detalleDonativo").getString("cantidadKg") + " Cantidad PZA: " +
                                jsonArray.getJSONObject(i).getJSONObject("detalleDonativo").getString("cantidadPza");

                        current.entradaSalidaDescripcion = jsonArray.getJSONObject(i).getJSONObject("detalleDonativo").getString("comentarios") ;

                        current.entradaSalidaDireccionC = current.detalle2;



                        current.entradaSalidaPesoT = jsonArray.getJSONObject(i).getJSONObject("detalleDonativo").getString("cantidadKg") + "Kg";
                        current.entradaSalidaPesoU = String.valueOf (Double.valueOf(jsonArray.getJSONObject(i).getJSONObject("detalleDonativo").getString("cantidadKg")) /
                                Double.valueOf(jsonArray.getJSONObject(i).getJSONObject("detalleDonativo").getString("cantidadPza"))) + "Kg" ;


                        if(jsonObject!= null) {
                            current.entradaSalidaTelefonoC = jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("contactoDonador").getString("nombre");
                            current.entradaSalidaUnidad = jsonArray.getJSONObject(i).getJSONObject("detalleDonativo").getString("cantidadPza") + "PZA";
                            current.entradaSalidaNombreC =
                                    jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("contactoDonador").getString("nombre")
                                            + " " + jsonObject.getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("contactoDonador").getString("apellido");
                            current.entradaSalidaNombrePro = jsonObject.getJSONObject("_embedded").getJSONObject("producto").getString("nombre");
                        }


                        String s = "";
                        String[] myName = current.entradaSalidaNombrePro.split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;


                        ContactsItems.add(current);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }




                break;
            case GetSalidas:

                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("salidasalmacen"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getJSONObject("centroComunitario").getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;

                        String myDate= jsonArray.getJSONObject(i).getString("fechaSalida").substring(0,10);
                        String strDateTime="";
                        try {
                            strDateTime = oFormatter.format(iFormatter.parse(myDate));

                        } catch (ParseException ex) {
                            ex.printStackTrace();

                        }
                        current.entradaSalidaFecha= "Fecha: " +strDateTime;

                        current.entradaSalidaNombrePro = jsonArray.getJSONObject(i).getJSONObject("centroComunitario").getString("nombre");
                        current.entradaSalidaDescripcion =   jsonArray.getJSONObject(i).getString("motivo");
                        current.entradaSalidaCantidad = "Cantidad: "+jsonArray.getJSONObject(i).getString("cantidad");

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case GetOfertasDonativos:
                try {
                    jsonArray = ((new JSONObject(response)).getJSONObject("_embedded").getJSONArray("detalledonativo"));
                    for (int i = 0; i <  (new JSONObject(response).getJSONObject("page")).getInt("totalElements") ; i++) {

                        ContactItem current = new ContactItem();

                        String s = "";
                        String[] myName = jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("producto").getString("nombre").split(" ");
                        for (int a = 0; a < myName.length ; a++) {
                            s += myName[a].charAt(0);
                            if (a==2)
                                break;
                        }
                        current.iniciales=s;
                        String myDate= jsonArray.getJSONObject(i).getString("fechaConsumoLimite").substring(0,10);
                        String strDateTime="";
                        try {
                            strDateTime = oFormatter.format(iFormatter.parse(myDate));

                        } catch (ParseException ex) {
                            ex.printStackTrace();

                        }
                        current.donativoProducto = jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("producto").getString("nombre");
                        current.donativoPeso = jsonArray.getJSONObject(i).getString("cantidadKg") ;
                        current.donativoFechaCaducidad = strDateTime;

                        if (jsonArray.getJSONObject(i).getBoolean("cosecha"))
                        current.donativoCosechado = "Se necesitó cosechar" ;
                        else
                            current.donativoCosechado = "No se necesitó cosechar";
                        current.donativoPagoCosecha= jsonArray.getJSONObject(i).getString("pagoCosecha");
                        current.donativoEmbalaje=  jsonArray.getJSONObject(i).getString("cantidadEmbalaje");

                        jsonObject = new JSONObject(new RequestGet().requestGet(jsonArray.getJSONObject(i).getJSONObject("_links")
                                .getJSONObject("embalaje").getString("href")));


                         myDate=  jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("donativo").getString("fechaAcopio").substring(0,10);
                         strDateTime="";
                        try {
                            strDateTime = oFormatter.format(iFormatter.parse(myDate));

                        } catch (ParseException ex) {
                            ex.printStackTrace();

                        }

                        current.donativoTipoEmbalaje= jsonObject.getString("tipoEmbalaje");
                        current.donativoFechaAcopio=myDate;
                        current.donativoDomicilioAcopio= jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("direccionAcopio").getString("calle")
                         + "" +jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("direccionAcopio").getString("numero");
                        current.donativoTelefono= jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("bancoAlimentos").getJSONObject("contacto").getString("telefono");
                        current.donativoExtension= jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("bancoAlimentos").getJSONObject("contacto").getString("extension");;
                        current.donativoCelular= jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("bancoAlimentos").getJSONObject("contacto").getString("celular");
                        current.donativoTransporte= jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("donativo").getJSONObject("transportesUnidades").getString("tipoUnidad");
                        current.donativoEspecificaciones=jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("donativo").getString("especificaciones");
                        current.donativoProcurador=jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("bancoAlimentos").getJSONObject("contacto").getString("nombre");;
                        current.donativoProcuradorCorreo=jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("bancoAlimentos").getJSONObject("contacto").getString("email");;
                        current.donativoProcuradorTelefono=jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("bancoAlimentos").getJSONObject("contacto").getString("telefono");;
                        current.donativoProcuradorCelular=jsonArray.getJSONObject(i).getJSONObject("_embedded").getJSONObject("bancoAlimentos").getJSONObject("contacto").getString("celular");;

                        current.detalle1 = "Fecha de acopio: "+ current.donativoFechaAcopio;
                        current.detalle2 =  "Transporte : "+ current.donativoTransporte + "\n Procurador: " + current.donativoProcurador;

                        ContactsItems.add(current);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
        }


        return ContactsItems;
    }



}
