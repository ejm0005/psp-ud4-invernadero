public class Fase3_Experimentos {
    public static void main(String[] args) {
        experimentoA();
        experimentoB();
        experimentoC();
    }

    public static void experimentoA() {
        System.out.println("Experimento A: informe() sin esperar");
        Sensor temperatura = new Temperatura();
        Thread hiloTemperatura = new Thread(temperatura, "sensor-Temperatura");
        hiloTemperatura.start();
        System.out.println("Sin esperar:");
        System.out.println("Informe: " + temperatura.informe());
        System.out.println("Al esperar:");
        try{
            hiloTemperatura.join();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Informe: " + temperatura.informe());
    }
    public static void experimentoB() {
        System.out.println("\nExperimento B: run() en vez de start()");
        ProgramadorRiego riego = new ProgramadorRiego(3);
        long inicio = System.currentTimeMillis();
        riego.run();
        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;
        System.out.println("Tiempo de ejecución: " + tiempoTotal + " milisegundos");
        System.out.println("Estado al final: " + riego.getState());
    }
    public static void experimentoC() {
        System.out.println("\nExperimento C: LLamada doble a start()");
        ProgramadorRiego riego = new ProgramadorRiego(3);
        System.out.println("Iniciando hilo...");
        riego.start();
        try{
            riego.join();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Hilo finalizado. Volviendo a ejecutar:");
        try{
            riego.start();
        }catch(IllegalThreadStateException e){
            System.out.println("ERROR FATAL: Un thread no se piede reutilizar o reiniciar una vez haya muerto.");

        }
    }
}
