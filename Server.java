/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Сервер запущен. Ожидание подключения клиента...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключен: " + clientSocket);

                Thread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientThread extends Thread {
    private final Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Чтение размеров матрицы
            int rows = inputStream.readInt();
            int cols = inputStream.readInt();
            int[][] matrix = new int[rows][cols];

            // Чтение матрицы
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = inputStream.readInt();
                }
            }

            System.out.println("Получена матрица:");
            for (int[] row : matrix) {
                System.out.println(Arrays.toString(row));
            }

            // Поиск максимального и минимального элементов
            int maxElement = matrix[0][0];
            int minElement = matrix[0][0];
            for (int[] row : matrix) {
                for (int element : row) {
                    if (element > maxElement) {
                        maxElement = element;
                    }
                    if (element < minElement) {
                        minElement = element;
                    }
                }
            }

            // Отправка результатов клиенту
            outputStream.writeInt(maxElement);
            outputStream.writeInt(minElement);
            outputStream.flush();

            clientSocket.close();
            System.out.println("Клиент отключен: " + clientSocket);
        } catch (IOException e) {
        }
    }
}
