package ua.kiev.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
        //Создаем Єкземпляр месседж листа
    private static final MessageList msgList = new MessageList();

    //Обект джейсон
    private final Gson gson;

    //Создаем константу колекции
    private final List<Message> list = new LinkedList<>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

//вытаскует смс
    public synchronized String toJSON(int n) {
        if (n >= list.size()) return null;
        return gson.toJson(new JsonMessages(list, n));
    }
}
