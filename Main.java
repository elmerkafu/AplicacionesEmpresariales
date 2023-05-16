package org.example;

import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class conectDB {

    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("1. Insertar Pedido");
            System.out.println("2. Insertar Cliente");
            System.out.println("3. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        inserPedido();
                        break;
                    case 2:
                        inserCliente();
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    public static void inserCliente() {

        String nombres;
        String apellidos;
        String direccion;
        String telefono;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/floreria";
            Connection connection = DriverManager.getConnection(url, "root", "root");

            Scanner sn = new Scanner(System.in);
            System.out.println("Escribe los nombres completos");
            nombres = sn.nextLine();
            System.out.println("Escribe el apellidos completos");
            apellidos = sn.nextLine();
            System.out.println("Escribe la direccion del cliente");
            direccion = sn.nextLine();
            System.out.println("Escribe el telefono del cliente");
            telefono = sn.nextLine();


            String query = "INSERT INTO cliente (NomCli, ApeCli, DriCli, TeleCli) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombres);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, direccion);
            preparedStatement.setString(4, telefono);

            int filaAfectada = preparedStatement.executeUpdate();
            System.out.println("Se inserto el cliente : " + nombres + " " + apellidos+ " Total de filas Afectadas: " + filaAfectada);

            preparedStatement.close();
            connection.close();


        } catch (ClassNotFoundException | SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

    public static void inserPedido() {

        String fecha;
        String monto;
        String idCliente;
        String idVendedor;
        String idTransp;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/floreria";
            Connection connection = DriverManager.getConnection(url, "root", "root");

            Scanner sn = new Scanner(System.in);
            System.out.println("Ingresa Fecha del pedido");
            fecha = sn.nextLine();
            System.out.println("Ingresa el monto del pedido");
            monto = sn.nextLine();
            System.out.println("Ingresa el id del cliente");
            idCliente = sn.nextLine();
            System.out.println("Ingresa el id del vendedor");
            idVendedor = sn.nextLine();
            System.out.println("Ingresa el id del transportista");
            idTransp = sn.nextLine();

            String query = "INSERT INTO pedido (Fecha, Monto, idCliente, idVendedor, idTransp) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fecha);
            preparedStatement.setString(2, monto);
            preparedStatement.setString(3, idCliente);
            preparedStatement.setString(4, idVendedor);
            preparedStatement.setString(5, idTransp);

            int filaAfectada = preparedStatement.executeUpdate();
            System.out.println("Se inserto el pedido del cliente con el ID : " + idCliente + " Total de filas Afectadas: " + filaAfectada);

            preparedStatement.close();
            connection.close();


        } catch (ClassNotFoundException | SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }
}
