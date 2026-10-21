public class Fase2_Concurrente {
    public static void main(String[] args){
        System.out.println("VERSIÓN CONCURRENTE:");
        long inicio = System.currentTimeMillis();
        Sensor temperatura = new Temperatura();
        Sensor luz = new Luz();
        Sensor humedad = new Humedad();
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread hiloTemperatura = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");
        Thread hiloHumedad = new Thread(humedad, "sensor-Humedad");

        hiloTemperatura.start();
        hiloLuz.start();
        hiloHumedad.start();
        riego.start();

        System.out.println("Se han iniciado todos los hilos:");
        try{
            hiloTemperatura.join();
            hiloLuz.join();
            hiloHumedad.join();
            riego.join();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        System.out.println(temperatura.informe());
        System.out.println(luz.informe());
        System.out.println(humedad.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo de ejecución: " + tiempoTotal + " milisegundos");
    }
}
