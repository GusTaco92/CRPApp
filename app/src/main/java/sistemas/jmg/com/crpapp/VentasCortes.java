package sistemas.jmg.com.crpapp;

public class VentasCortes {

    private String NUM_CLIENTE;
    private String SUMATOTAL;
    private String CLIENTE;
    private String ESTATUS;
    private String ESTADO_PED;
    private String CIUDAD;
    private String VENTA;
    private String RUTA;
    private String SEMANA;
    private String BLOQUEADO;
    private String FOLIODESBLOQUEO;
    private String FECHADEIMPRESION;

    public String getFECHADEIMPRESION() {
        return FECHADEIMPRESION;
    }

    public void setFECHADEIMPRESION(String FECHADEIMPRESION) {
        this.FECHADEIMPRESION = FECHADEIMPRESION;
    }

    public String getFOLIODESBLOQUEO() {
        return FOLIODESBLOQUEO;
    }

    public void setFOLIODESBLOQUEO(String FOLIODESBLOQUEO) {
        this.FOLIODESBLOQUEO = FOLIODESBLOQUEO;
    }

    public String getSUMATOTAL() {
        return SUMATOTAL;
    }

    public String getBLOQUEADO() {
        return BLOQUEADO;
    }

    public void setBLOQUEADO(String BLOQUEADO) {
        this.BLOQUEADO = BLOQUEADO;
    }

    public void setSUMATOTAL(String SUMATOTAL) {
        this.SUMATOTAL = SUMATOTAL;
    }

    public String getCLIENTE() {
        return CLIENTE;
    }

    public void setCLIENTE(String CLIENTE) {
        this.CLIENTE = CLIENTE;
    }

    public String getESTATUS() {
        return ESTATUS;
    }

    public void setESTATUS(String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }

    public String getESTADO_PED() {
        return ESTADO_PED;
    }

    public void setESTADO_PED(String ESTADO_PED) {

        this.ESTADO_PED = ESTADO_PED;
    }

    public String getCIUDAD() {
        return CIUDAD;
    }

    public void setCIUDAD(String CIUDAD) {
        this.CIUDAD = CIUDAD;
    }

    public String getNUM_CLIENTE() {
        return NUM_CLIENTE;
    }

    public void setNUM_CLIENTE(String NUM_CLIENTE) {
        this.NUM_CLIENTE = NUM_CLIENTE;
    }

    public String getVENTA() {
        return VENTA;
    }

    public void setVENTA(String VENTA) {
        this.VENTA = VENTA;
    }

    public String getRUTA() {
        return RUTA;
    }

    public void setRUTA(String RUTA) {
        this.RUTA = RUTA;
    }

    public String getSEMANA() {
        return SEMANA;
    }

    public void setSEMANA(String SEMANA) {
        this.SEMANA = SEMANA;
    }

    public String getCLAVE_VENDEDOR() {
        return CLAVE_VENDEDOR;
    }

    public void setCLAVE_VENDEDOR(String CLAVE_VENDEDOR) {
        this.CLAVE_VENDEDOR = CLAVE_VENDEDOR;
    }

    private String CLAVE_VENDEDOR;

    public VentasCortes(String num_cliente, String venta, String ruta, String semana, String clave_vendedor, String cliente,String estatus,String estado_ped,String ciudad, String SumaTotal,String bloqueado,String foliodesbloqueo,String fechadeimpresion){
        this.NUM_CLIENTE=num_cliente;
        this.VENTA=venta;
        this.RUTA=ruta;
        this.SEMANA=semana;
        this.CLAVE_VENDEDOR=clave_vendedor;
        this.CLIENTE= cliente;
        this.ESTATUS=estatus;
        this.ESTADO_PED=estado_ped;
        this.CIUDAD=ciudad;
        this.SUMATOTAL=SumaTotal;
        this.BLOQUEADO=bloqueado;
        this.FOLIODESBLOQUEO=foliodesbloqueo;
        this.FECHADEIMPRESION=fechadeimpresion;
    }
}
