package Model;

import Exceptions.NoSuchIDException;

import java.util.ArrayList;

public class Message {
    private int id;
    private String messageText;
    private String usernameOfSender;
    private Message replyOnMessage = null;
    private ArrayList<Message> replies = new ArrayList<>();
    private static int lastID = 0;
    private static ArrayList<Message> messages = new ArrayList<>();

    /**
     * This constructor creates a Message.
     * @param messageText String : The text of the message content.
     * @param usernameOfSender String : username of the sender of the message.
     */
    public Message(String messageText, String usernameOfSender) {
        lastID ++;
        id = lastID;
        this.messageText = messageText;
        this.usernameOfSender = usernameOfSender;
        messages.add(this);
    }

    /**
     * This constructor creates a Message which is reply of another Message (replyOnMessage)
     * @param messageText String : The text of the message content.
     * @param usernameOfSender String : username of the sender of the message.
     * @param replyOnMessage Message on which it is replied.
     */
    public Message(String messageText, String usernameOfSender, Message replyOnMessage) {
        lastID ++;
        id = lastID;
        this.messageText = messageText;
        this.usernameOfSender = usernameOfSender;
        this.replyOnMessage = replyOnMessage;
        replyOnMessage.replies.add(this);
        messages.add(this);
    }

    /** This method edits text of the Message object
     * @param newText String : The old text of Message (its content) will be replaced by this.
     */
    public void edit(String newText) {
        messageText = newText;
    }

    /**
     * This method deletes the Message object and removes it from reply if it is reply of another Message , but
     *      it may continue to exist if some Messages are reply to it.
     */
    public void delete() {
        messages.remove(this);
        if (replyOnMessage != null) {
            replyOnMessage.replies.remove(this);
        }
    }
    
    /**
     * This method takes id of a Message and edits its text. If the id be invalid it throws a NoSuchIDException.
     * @param messageToEditID int : ID of message to edit.
     * @param newText String : The old text of Message (its content) will be replaced by this.
     * @exception NoSuchIDException : If the id be invalid.
     */
    public static void edit(int messageToEditID , String newText) throws NoSuchIDException {
        Message messageToEdit = getMessageById(messageToEditID);
        messageToEdit.edit(newText);
    }
    
    /**
     * This method takes id of a Message and edits its text. If the id be invalid it throws a NoSuchIDException.
     * @param messageToDeleteID int : ID of Message to delete.
     * @throws NoSuchIDException If the id be invalid.
     */
    public static void delete(int messageToDeleteID) throws NoSuchIDException {
        Message messageToDelete = getMessageById(messageToDeleteID);
        messageToDelete.delete();
    }
    
    /**
     * This method takes ID of a Message and returns the Message with that id and if id be invalid it throws a NoSuchIDException.
     * @param id int : ID of the message to get.
     * @exception NoSuchIDException If the id be invalid.
     */
    public static Message getMessageById(int id) throws NoSuchIDException {
        for (Message message :
                messages) {
            if (message.id == id) {
                return message;
            }
        } throw new NoSuchIDException("Message",id);
    }
}