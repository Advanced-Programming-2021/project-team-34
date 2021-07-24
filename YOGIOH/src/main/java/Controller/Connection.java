package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    static Socket socket;
    static String host = "localhost";
    public static String sendMessageToTheServer(String messageToSend){
        try {
            socket = new Socket("4.tcp.ngrok.io" , 10866);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.writeUTF(messageToSend);
            dataOutputStream.flush();
            String result = dataInputStream.readUTF();

            dataOutputStream.close();
            socket.close();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed to connect to the server !";
    }

    public static void setHost(String host) {
        Connection.host = host;
    }
}
