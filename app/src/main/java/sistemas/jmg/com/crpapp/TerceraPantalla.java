package sistemas.jmg.com.crpapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TerceraPantalla extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    RecyclerView reciclo;
    ArrayList<VentasCortes> ListaDatos=new ArrayList<>();
    VentasAdapter x;
    RequestQueue rq,respuestaRq;
    JsonRequest jrq,jsonRespuesta;
    String rutaSecundaria;
    String claveSecundaria;
    String numerocliente;
    String Semana;
    int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_pantalla);
        reciclo =(RecyclerView)findViewById(R.id.rvTerceraVentana);
        rutaSecundaria=getIntent().getExtras().getString("RUTAPRINCIPAL");
        claveSecundaria=getIntent().getExtras().getString("CLAVEPRINCIPAL");
        numerocliente=getIntent().getExtras().getString("NUMEROCLIENTE");
        rq= Volley.newRequestQueue(getBaseContext());
        respuestaRq=Volley.newRequestQueue(getBaseContext());
        tipo=getIntent().getExtras().getInt("TIPO");
        Semana=getIntent().getExtras().getString("SEMANA");
        RellenarList();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error.getMessage());
        Toast.makeText(getBaseContext(),"Error de conexión al servidor"+error.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonarray=response.optJSONArray("datos");
        try {
            JSONArray lista=jsonarray.getJSONArray(0);
            for(int i=0;i < lista.length();i++){
                JSONObject TEMP= (JSONObject) lista.get(i);
                ListaDatos.add(new VentasCortes(TEMP.getString("DetCort_NumeroCliente"),TEMP.getString("DetCort_Venta"),TEMP.getString("DetCort_Ruta"),TEMP.getString("Det_Semana"),TEMP.getString("DetCort_ClaveVendedor"),TEMP.getString("DetCort_NombreCliente"),TEMP.getString("DetCort_Estatus"),TEMP.getString("DetCort_EstadoPedido"),TEMP.getString("DetCort_Ciudad"),TEMP.getString("DetCort_Total"),TEMP.getString("DetCort_Bloqueado"),TEMP.getString("DetCort_FolioDesbloqueo"),TEMP.getString("DetCort_FechaHoraImpresion")));
            }
            LinearLayoutManager constrain= new LinearLayoutManager(this);
            RecyclerView.LayoutManager rvLayoutMag=constrain;
            reciclo.setLayoutManager(rvLayoutMag);
            x= new VentasAdapter(this,ListaDatos);
            reciclo.setAdapter(x);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void RellenarList()
    {
        if(ListaDatos.size() > 0)
        {ListaDatos.clear();x.notifyDataSetChanged();}
        String URL="";
        if(tipo == 0)
        {
            URL="http://pruebasjmg.sytes.net/Welcome/MobileAppNew/"+claveSecundaria.toUpperCase()+"/"+rutaSecundaria.toUpperCase().trim()+"/"+numerocliente+"/";
        }else {
            if (tipo == 1) {
                URL="http://pruebasjmg.sytes.net/Welcome/MobileAppNew/"+claveSecundaria.toUpperCase()+"/"+rutaSecundaria.toUpperCase().trim()+"/"+numerocliente+"/";
            }else{
                if(tipo == 2){
                    URL="http://pruebasjmg.sytes.net/Welcome/MobileAppNew/"+claveSecundaria.toUpperCase()+"/"+rutaSecundaria.toUpperCase().trim()+"/"+numerocliente+"/";
                }
            }
        }
        jrq=new JsonObjectRequest(Request.Method.GET,URL,null,this,this);
        rq.add(jrq);
    }
}