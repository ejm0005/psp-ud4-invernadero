public class Fase4_Vigilante {
    public static void main(String[] args) {
        Sensor temperatura = new Temperatura();
        Sensor luz = new Luz();
        Sensor humedad = new Humedad();
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread hiloTemperatura = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");
        Thread hiloHumedad = new Thread(humedad, "sensor-Humedad");

        System.out.println("Estados antes de arrancar:");
        System.out.println(hiloTemperatura.getName() + ": " + hiloTemperatura.getState());
        System.out.println(hiloLuz.getName() + ": " + hiloLuz.getState());
        System.out.println(hiloHumedad.getName() + ": " + hiloHumedad.getState());
        System.out.println(riego.getName() + ": " + riego.getState());

        Thread vigilante = new Thread(() -> {
            try{
                while(true){
                    System.out.println("Vigilante: Estados actuales -> Temperatura: " + hiloTemperatura.getState() + " Luz: " + hiloLuz.getState() + " Humedad: " + hiloHumedad.getState() + " Riego: " + riego.getState());
                    Thread.sleep(300);
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }, "hilo-vigilante");
        vigilante.setDaemon(true);
        vigilante.start();
        hiloTemperatura.start();
        hiloLuz.start();
        hiloHumedad.start();
        riego.start();

        try {
            hiloTemperatura.join();
            hiloLuz.join();
            hiloHumedad.join();
            riego.join();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("Estados después de ejecutar el vigilante:");
        System.out.println(hiloTemperatura.getName() + ": " + hiloTemperatura.getState());
        System.out.println(hiloLuz.getName() + ": " + hiloLuz.getState());
        System.out.println(hiloHumedad.getName() + ": " + hiloHumedad.getState());
        System.out.println(riego.getName() + ": " + riego.getState());
    }
}
