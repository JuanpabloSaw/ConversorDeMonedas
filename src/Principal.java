import com.google.gson.JsonSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int opcionElegida = 0;

        ConsultaApi consulta = new ConsultaApi();
        Monedas monedas = new Monedas(consulta);


        List<String> respuestas = new ArrayList<>();

        String menu = """
                \n***************************************************
                *** Sea bienvenido al Conversor de Monedas ***
                
                1)Peso Colombiano ==> Dolar Estadounidense
                2)Peso Colombiano ==> Euro
                3)Peso Colombiano ==> Libra Esterlina
                4)Dolar Estadounidense ==> Peso Colombiano
                5)Euro ==> Peso Colombiano
                6)Libra Esterlina ==> Peso Colombiano
                
                7)Otra opcion de conversion
                
                8)Salir
                ***************************************************
                """;
        while (opcionElegida != 8) {
            try {
                System.out.println(menu);
                opcionElegida = Integer.parseInt(lectura.nextLine());

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                switch (opcionElegida) {
                    case 1:
                        monedas.almacenarValores("COP","USD");
                        respuestas.add(formattedDate + " - " + monedas.obtenerMensajeRespuesta());
                        break;
                    case 2:
                        monedas.almacenarValores("COP","EUR");
                        respuestas.add(formattedDate + " - " + monedas.obtenerMensajeRespuesta());
                        break;
                    case 3:
                        monedas.almacenarValores("COP","GBP");
                        respuestas.add(formattedDate + " - " + monedas.obtenerMensajeRespuesta());
                        break;
                    case 4:
                        monedas.almacenarValores("USD","COP");
                        respuestas.add(formattedDate + " - " + monedas.obtenerMensajeRespuesta());
                        break;
                    case 5:
                        monedas.almacenarValores("EUR","COP");
                        respuestas.add(formattedDate + " - " + monedas.obtenerMensajeRespuesta());
                        break;
                    case 6:
                        monedas.almacenarValores("GBP","COP");
                        respuestas.add(formattedDate + " - " + monedas.obtenerMensajeRespuesta());
                        break;
                    case 7:
                        monedas.almacenarValoresPersonalizados();
                        respuestas.add(formattedDate + " - " + monedas.obtenerMensajeRespuesta());
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            } catch (JsonSyntaxException | NullPointerException e){
                System.out.println("Error. Ingrese solo codigos de monedas validos.");
            }catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error. Ingrese un valor numerico valido.");
            }
        }
        GuardarArchivos guardar = new GuardarArchivos();
        guardar.guardarJson(respuestas);

        System.out.println("Finalizando Programa");
    }
}