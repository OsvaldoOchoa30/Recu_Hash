import java.util.Scanner;
import models.Business;
import models.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashTable sumaHash = new HashTable(1000);
        HashTable multiHash = new HashTable(1000);

        String line = "";
        String splitBy = ",";


        long totalInsertionTimeHash1 = 0;
        long totalInsertionTimeHash2 = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("bussines.csv"));
            while ((line = br.readLine()) != null) {
                String[] businessData = line.split(splitBy);
                Business business = new Business(businessData[0], businessData[1], businessData[2], businessData[3], businessData[4]);

                long startTime = System.nanoTime();
                sumaHash.put(business.getId(), business, true);
                long endTime = System.nanoTime();
                totalInsertionTimeHash1 += (endTime - startTime);

                startTime = System.nanoTime();
                multiHash.put(business.getId(), business, false);
                endTime = System.nanoTime();
                totalInsertionTimeHash2 += (endTime - startTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Tiempo Funcion Hash Suma: " + totalInsertionTimeHash1 + " ns");
        System.out.println("Tiempo Funcion Hash Multiplicacion: " + totalInsertionTimeHash2 + " ns");

        String option;
        boolean flag = false;
        do {
            System.out.println("1. Agregar Negocio");
            System.out.println("2. Ver Todos Los Negocios");
            System.out.println("3. Buscar Negocio");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            option = scanner.nextLine();


            switch (option) {
                case "1":
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String address = scanner.nextLine();
                    System.out.print("Ciudad: ");
                    String city = scanner.nextLine();
                    System.out.print("Estado: ");
                    String state = scanner.nextLine();
                    Business business = new Business(id, name, address, city, state);

                    long startTime = System.nanoTime();
                    sumaHash.put(id, business, true);
                    long endTime = System.nanoTime();
                    System.out.println("Tiempo Funcion Hash Suma: " + (endTime - startTime) + " ns");

                    startTime = System.nanoTime();
                    multiHash.put(id, business, false);
                    endTime = System.nanoTime();
                    System.out.println("Funcion Hash Multiplicacion: " + (endTime - startTime) + " ns");
                    break;
                case "2":
                    long startTimeDisplay = System.nanoTime();
                    System.out.println("HashTable con Suma:");
                    sumaHash.displayAll();
                    long endTimeDisplay = System.nanoTime();
                    startTimeDisplay = System.nanoTime();

                    System.out.println("HashTable con Multiplicacion:");
                    multiHash.displayAll();
                    endTimeDisplay = System.nanoTime();

                    //System.out.println("Tiempo para mostrar todos los negocios con Hash Suma: " + (endTimeDisplay - startTimeDisplay) + " ns");
                    //System.out.println("Tiempo para mostrar todos los negocios con Hash Multiplicacion: " + (endTimeDisplay - startTimeDisplay) + " ns");
                    break;
                case "3":
                    System.out.print("Ingresa el ID del Negocio: ");
                    String searchId = scanner.nextLine();

                    long startTimeGet = System.nanoTime();
                    Business found1 = sumaHash.get(searchId, true);
                    long endTimeGet = System.nanoTime();
                    System.out.println("Tiempo Funcion Hash Suma: " + (endTimeGet - startTimeGet) + " ns");

                    if (found1 != null) {
                        System.out.println("Tiempo Funcion Hash Suma: " + found1);
                    } else {
                        System.out.println("Funcion Hash Suma: Negocio no encontrado");
                    }

                    startTimeGet = System.nanoTime();
                    Business found2 = multiHash.get(searchId, false);
                    endTimeGet = System.nanoTime();
                    System.out.println("Tiempo Funcion Hash Multiplicacion: " + (endTimeGet - startTimeGet) + " ns");

                    if (found2 != null) {
                        System.out.println("Funcion Hash Multiplicacion: " + found2);
                    } else {
                        System.out.println("Funcion Hash Multiplicacion: Negocio no encontrado");
                    }
                    break;
                case "4":
                    System.out.println("Cerrando Programa...");
                    flag = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!flag);
    }
}