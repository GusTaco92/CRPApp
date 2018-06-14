package sistemas.jmg.com.crpapp;

public class ListaClientes
{
    private String NUMEROCLIENTE;
    private String NOMBRECLIENTE;
    private String CLAVEVENDEDOR;
    private String RUTA;
    private int TIPO;
    private String SEMANA;

    public int getTIPO() {
        return TIPO;
    }

    public void setTIPO(int TIPO) {
        this.TIPO = TIPO;
    }

    public String getSEMANA() {
        return SEMANA;
    }

    public void setSEMANA(String SEMANA) {
        this.SEMANA = SEMANA;
    }

    public String getNUMEROCLIENTE() {
        return NUMEROCLIENTE;
    }

    public void setNUMEROCLIENTE(String NUMEROCLIENTE) {
        this.NUMEROCLIENTE = NUMEROCLIENTE;
    }

    public String getNOMBRECLIENTE() {
        return NOMBRECLIENTE;
    }

    public void setNOMBRECLIENTE(String NOMBRECLIENTE) {
        this.NOMBRECLIENTE = NOMBRECLIENTE;
    }

    public String getCLAVEVENDEDOR() {
        return CLAVEVENDEDOR;
    }

    public void setCLAVEVENDEDOR(String CLAVEVENDEDOR) {
        this.CLAVEVENDEDOR = CLAVEVENDEDOR;
    }

    public String getRUTA() {
        return RUTA;
    }

    public void setRUTA(String RUTA) {
        this.RUTA = RUTA;
    }

    public ListaClientes(String NUMEROCLIENTE, String NOMBRECLIENTE, String CLAVEVENDEDOR, String RUTA, int TIPO, String semana) {
        this.NUMEROCLIENTE = NUMEROCLIENTE;
        this.NOMBRECLIENTE = NOMBRECLIENTE;
        this.CLAVEVENDEDOR = CLAVEVENDEDOR;
        this.RUTA = RUTA;
        this.TIPO = TIPO;
        this.SEMANA=semana;
    }
}
