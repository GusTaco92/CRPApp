package sistemas.jmg.com.crpapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class CRPaps extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    RecyclerView reciclo;
    ArrayList<ListaClientes> ListaDatosClientes=new ArrayList<>();
    RequestQueue rq,respuestaRq;
    JsonRequest jrq,jsonRespuesta;
    EditText Mensaje,RUTA;
    AdaptadorClientes x;
    String SemanaActual="";

    TextView SemanaVer,RutaVer,ClaveVer,FechaDelCorte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crpaps);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton Btn_Next = findViewById(R.id.BtnSiguiente);
        FloatingActionButton Btn_Previus = findViewById(R.id.BtnPrevio);
        reciclo =findViewById(R.id.rv);
        Btn_Previus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RellenarPrevius(v);
            }
        });

        Btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SemanaVer = findViewById(R.id.TxSemanaActual);
                String Cvendedor=Mensaje.getText().toString().trim();
                String Cruta=RUTA.getText().toString().trim();
                final String Csemana=SemanaVer.getText().toString().trim();
                if(!Cvendedor.isEmpty() && !Cruta.isEmpty())
                {
                    if (SemanaActual.equals("") || Integer.parseInt(SemanaActual) <= Integer.parseInt(SemanaVer.getText().toString()))
                    {
                        Toast.makeText(getBaseContext(),"No hay mas que avanzar",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Integer.parseInt(SemanaActual) == Integer.parseInt(SemanaVer.getText().toString())+1){
                            RellenarListCliente(v);
                        }
                        if(ListaDatosClientes.size() > 0)
                        {ListaDatosClientes.clear();x.notifyDataSetChanged();}
                        String URL="http://pruebasjmg.sytes.net/Welcome/ClientesHistorialSiguiente/"+Cvendedor.toUpperCase()+"/"+Cruta.toUpperCase()+"/"+Csemana.toUpperCase()+"/0";
                        jsonRespuesta= new JsonObjectRequest(Request.Method.GET,URL,null,new Response.Listener<JSONObject>() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onResponse(JSONObject response) {
                                reciclo = findViewById(R.id.rv);
                                //ListaDatos=new ArrayList<>();
                                JSONArray jsonarray=response.optJSONArray("datos");
                                JSONArray fechaC=response.optJSONArray("fechadelcorte");
                                try {
                                    JSONArray lista=jsonarray.getJSONArray(0);
                                    JSONArray fechaCarry=fechaC.getJSONArray(0);
                                    for(int i=0;i < lista.length();i++){
                                        JSONObject TEMP= (JSONObject) lista.get(i);
                                        ListaDatosClientes.add(new ListaClientes(TEMP.getString("DetCort_NumeroCliente"),TEMP.getString("DetCort_NombreCliente"),RUTA.getText().toString(),SemanaVer.getText().toString(),2,Csemana));
                                        if(i == lista.length()-1)
                                        {
                                            SemanaVer.setText(TEMP.getString("Det_Semana"));
                                        }
                                    }
                                    if(ListaDatosClientes.size() > 0)
                                    {
                                        RecyclerView.LayoutManager rvLayoutMag= new LinearLayoutManager(getBaseContext());
                                        reciclo.setLayoutManager(rvLayoutMag);
                                        x= new AdaptadorClientes(getBaseContext(),ListaDatosClientes);
                                        reciclo.setAdapter(x);
                                        RutaVer.setText("Ruta: "+RUTA.getText());
                                        ClaveVer.setText("Clave de vendedor: "+Mensaje.getText().toString().toUpperCase());
                                        for (int j=0;j < fechaCarry.length();j++){
                                            JSONObject TEMP2= (JSONObject) fechaCarry.get(j);
                                            FechaDelCorte.setText(TEMP2.getString("Rut_FechaCorte"));
                                        }
                                    }else
                                    {
                                        Toast.makeText(getBaseContext(),"No se encontró ningún dato",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getBaseContext(),"Error de conexión",Toast.LENGTH_SHORT).show();
                            }
                        });
                        respuestaRq.add(jsonRespuesta);
                    }
                }else{
                    Snackbar.make(v,"Escriba una clave de vendedor y una ruta válidos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RellenarListCliente(view);
            }
        });
        Mensaje=findViewById(R.id.EditTextClaveVendedor);
        RUTA=findViewById(R.id.TXTRuta);
        SemanaVer=findViewById(R.id.TxSemanaActual);
        RutaVer=findViewById(R.id.TxWRuta);
        ClaveVer=findViewById(R.id.TwVendedor);
        FechaDelCorte=findViewById(R.id.TwCorteRuta);
        rq= Volley.newRequestQueue(getBaseContext());
        respuestaRq=Volley.newRequestQueue(getBaseContext());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error.getMessage());
        Toast.makeText(getBaseContext(),"Error de conexión al servidor"+error.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        reciclo = findViewById(R.id.rv);
        TextView SEMANA= findViewById(R.id.TxSemanaActual);
        String Csemana=SEMANA.getText().toString().trim();
        JSONArray jsonarray=response.optJSONArray("datos");
        JSONArray fechaC=response.optJSONArray("fechadelcorte");

        try {
            JSONArray lista=jsonarray.getJSONArray(0);
            JSONArray fechaCarry=fechaC.getJSONArray(0);
            for(int i=0;i < lista.length();i++){
                JSONObject TEMP= (JSONObject) lista.get(i);
                ListaDatosClientes.add(new ListaClientes(TEMP.getString("DetCort_NumeroCliente"),TEMP.getString("DetCort_NombreCliente"),Mensaje.getText().toString().trim(),RUTA.getText().toString().trim(),0,Csemana));
                if(i == lista.length()-1)
                {
                    SemanaVer.setText(TEMP.getString("semanaActual"));
                    SemanaActual=TEMP.getString("semanaActual");
                }
            }
            if(ListaDatosClientes.size() > 0)
            {
                LinearLayoutManager constrain= new LinearLayoutManager(this);
                RecyclerView.LayoutManager rvLayoutMag=constrain;
                reciclo.setLayoutManager(rvLayoutMag);
                x= new AdaptadorClientes(this,ListaDatosClientes);
                reciclo.setAdapter(x);
                RutaVer.setText("Ruta: "+RUTA.getText());
                ClaveVer.setText("Clave de vendedor: "+Mensaje.getText().toString().toUpperCase());
                for (int j=0;j < fechaCarry.length();j++){
                    JSONObject TEMP2= (JSONObject) fechaCarry.get(j);
                    FechaDelCorte.setText(TEMP2.getString("Rut_FechaCorte"));
                }
            }else
            {
                Toast.makeText(getBaseContext(),"No se encontró ningún dato",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void RellenarListCliente(View view)
    {
        String Clav=Mensaje.getText().toString().trim();
        String Ruta=RUTA.getText().toString().trim();
        if(ListaDatosClientes.size() > 0)
        {ListaDatosClientes.clear();x.notifyDataSetChanged();}
        if(!Clav.isEmpty() && !Ruta.isEmpty())
        {
            String URL="http://pruebasjmg.sytes.net/Welcome/ClientesPorVendedor/"+Clav.toUpperCase()+"/"+Ruta.toUpperCase()+"/";
            jrq=new JsonObjectRequest(Request.Method.GET,URL,null,this,this);
            rq.add(jrq);
        }else{
            Snackbar.make(view,"Escriba una clave de vendedor y una ruta válidos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }


    public void RellenarPrevius(View view)
    {
        if(ListaDatosClientes.size() > 0)
        {ListaDatosClientes.clear();x.notifyDataSetChanged();}

        TextView SEMANA= findViewById(R.id.TxSemanaActual);
        final String Cvendedor=Mensaje.getText().toString().trim();
        final String Cruta=RUTA.getText().toString().trim();
        final String Csemana=SEMANA.getText().toString().trim();

        if(!Cvendedor.isEmpty() && !Cruta.isEmpty())
        {
            String URL="http://pruebasjmg.sytes.net/Welcome/ClientesHistorialAnterior/"+Cvendedor.toUpperCase()+"/"+Cruta.toUpperCase()+"/"+Csemana.toUpperCase();
            jsonRespuesta= new JsonObjectRequest(Request.Method.GET,URL,null,new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    JSONArray jsonarray=response.optJSONArray("datos");
                    JSONArray fechaC=response.optJSONArray("fechadelcorte");
                    try {
                        JSONArray lista=jsonarray.getJSONArray(0);
                        JSONArray fechaCarry=fechaC.getJSONArray(0);
                        for(int i=0;i < lista.length();i++){
                            JSONObject TEMP= (JSONObject) lista.get(i);
                            ListaDatosClientes.add(new ListaClientes(TEMP.getString("DetCort_NumeroCliente"),TEMP.getString("DetCort_NombreCliente"),RUTA.getText().toString(),SemanaVer.getText().toString(),1,Csemana));
                            if(i == lista.length()-1)
                            {
                                SemanaVer.setText(TEMP.getString("Det_Semana"));
                            }
                        }
                        if(ListaDatosClientes.size() > 0)
                        {
                            LinearLayoutManager constrain= new LinearLayoutManager(getBaseContext());
                            RecyclerView.LayoutManager rvLayoutMag=constrain;
                            reciclo.setLayoutManager(rvLayoutMag);
                            x= new AdaptadorClientes(getBaseContext(),ListaDatosClientes);
                            reciclo.setAdapter(x);
                            RutaVer.setText("Ruta: "+RUTA.getText());
                            ClaveVer.setText("Clave de vendedor: "+Mensaje.getText().toString().toUpperCase());
                            for (int j=0;j < fechaCarry.length();j++){
                                JSONObject TEMP2= (JSONObject) fechaCarry.get(j);
                                FechaDelCorte.setText(TEMP2.getString("Rut_FechaCorte"));
                            }
                        }else
                        {
                            Toast.makeText(getBaseContext(),"No se encontró ningún dato",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getBaseContext(),"Error de conexión",Toast.LENGTH_SHORT).show();
                }
            });
            respuestaRq.add(jsonRespuesta);
        }else{
            Snackbar.make(view,"Escriba una clave de vendedor y una ruta válidos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }
}
