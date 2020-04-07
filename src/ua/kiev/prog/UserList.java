package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserList {
    private final Gson gson;

    private static final UserList userList = new UserList();


    private final List<User> list = new LinkedList<>();

    public static UserList getInstance() {
        return userList;
    }

    private UserList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(User userList) {
        list.add(userList);
    }
    public synchronized int size(){return list.size();}
    public synchronized User get(int i){return list.get(i);}


    public synchronized boolean check(User name) {
        if(list.contains(name)){
            return true;
        }
        else
        return false;
    }
    public synchronized String toJSON() {
        List<User> allUserList = new ArrayList<>();
        for (User user : list){
            if (user != null)
                allUserList.add(user);
        }
        return gson.toJson(new UserLisr(allUserList));
    }

}
