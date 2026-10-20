import java.time.LocalTime;
public class ProgramadorRiego extends Thread{
    private int ciclosTotales;

    private int ciclosHechos = 0;
    private double litrosTotales = 0;
    private double litrosXRiego = 2.5;

    public ProgramadorRiego(){
        super("riego");
        this.ciclosTotales = 5;
    }
    @Override
    public void run(){
        for(int i=0; i<=ciclosTotales; i++){
            ciclosHechos++;
            String estado;
            if(i%2!=0){
                estado = "ABIERTA";
                litrosTotales += litrosXRiego;
            } else{
                estado = "CERRADA";
            }
            System.out.println(LocalTime.now() + " " + getName() + " " + "Ciclo: " + i + " Válvula:" + estado);
            try{
                Thread.sleep(700);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    public String informe(){
        return "Se han realizado " + ciclosHechos + " ciclos. Se han utilizado" + litrosTotales + " litros";
    }
}
