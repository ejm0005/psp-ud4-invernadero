public class Fase1_Secuencial {
    public static void main(String[] args){
        long inicio = System.currentTimeMillis();
        Sensor temperatura = new Temperatura();
        Sensor luz = new Luz();
        Sensor humedad = new Humedad();
        ProgramadorRiego riego = new ProgramadorRiego();

        temperatura.run();
        luz.run();
        humedad.run();
        riego.run();

        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        System.out.println(temperatura.informe());
        System.out.println(luz.informe());
        System.out.println(humedad.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo de ejecución: " + tiempoTotal + " milisegundos");
    }
}
