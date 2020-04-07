package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SendUser extends HttpServlet {

    UserList userList = UserList.getInstance();


    Gson gson = null;

    public SendUser(){
        gson = new GsonBuilder().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String send = null;
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        User user = new User(name, pass);
        if(userList.check(user)){
             send = (name);
        }
        else {
            send = "Good buy";
        }

        String json = gson.toJson(send);

        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
