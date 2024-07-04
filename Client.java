/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        try {
            try (Socket clientSocket = new Socket("localhost", 1234)) {
                System.out.println("Подключение к серверу: " + clientSocket);

                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                // Ввод размеров матрицы
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите количество строк матрицы: ");
                int rows = scanner.nextInt();
                System.out.print("Введите количество столбцов матрицы: ");
                int cols = scanner.nextInt();

                // Ввод элементов матрицы
                System.out.println("Введите элементы матрицы:");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        int element = scanner.nextInt();
                        outputStream.writeInt(element);
                    }
                }
                outputStream.flush();

                // Получение результатов от сервера
                int maxElement = inputStream.readInt();
                int minElement = inputStream.readInt();
                System.out.println("Максимальный элемент: " + maxElement);
                System.out.println("Минимальный элемент: " + minElement);
            }
        } catch (IOException e) {
        }
    }
}

