package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    final static int PORT = 12345;
    static boolean toContinue = true;
    public static void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (toContinue) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                        String request = dataInputStream.readUTF();
                        System.out.println("\n------------------------------------------------------\n" +
                                "request : {\n\t"+request+"\n}\n");
                        String result = RequestHandler.handleRequest(request);
                        if (request.equals("end1342tth22234t53erfdweds3qwae123etrg2t3qwg321")) {
                            toContinue = false;
                        }
                        dataOutputStream.writeUTF(result);

                        dataInputStream.close();
                        socket.close();
                        System.out.println("answer : {\n\t"+result+"\n}");//\n------------------------------------------------------\n" +
                        //"request : {\n\t"+request+"\n}\n
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }).start();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

