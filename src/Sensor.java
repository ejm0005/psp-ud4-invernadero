import java.time.LocalTime;

public abstract class Sensor implements Runnable {
    //Atributos
    private String nombre;
    private String unidad;
    private int periodo;
    private int numLecturas;
    private double umbralMinimo;
    private double umbralMaximo;

    //Estadísticas
    private int lecturas = 0;
    private double suma = 0;
    private double minimo = 0;
    private double maximo = 0;
    private int alertas = 0;

    public Sensor(String nombre, String unidad, int periodo, int numLecturas, double umbralMinimo, double umbralMaximo) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodo = periodo;
        this.numLecturas = numLecturas;
        this.umbralMinimo = umbralMinimo;
        this.umbralMaximo = umbralMaximo;
    }

    public abstract double medir();

    @Override
    public void run() {
        String hilo = Thread.currentThread().getName();
        for(int i = 0; i < numLecturas; i++){
            double valor = medir();
            lecturas++;
            suma += valor;
            if(valor < minimo){
                minimo = valor;
            }
            if(valor > maximo){
                maximo = valor;
            }
            boolean fueraRango = (valor < umbralMinimo || valor > umbralMaximo);
            if(fueraRango){
                alertas++;
            }
            System.out.println(LocalTime.now() + " " + hilo + " " + valor + " " + unidad + " " + (fueraRango ? "Alerta":""));
            try {
                Thread.sleep(periodo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public String informe(){
        double media = suma/lecturas;
        return "Sensor: " + nombre + ", Lecturas: " + numLecturas + ", Media: " + media + ", Minimo: " + minimo + ", Maximo: " + maximo + ", nº de alertas: " + alertas;
    }
}