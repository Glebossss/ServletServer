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
public class AllUser extends HttpServlet {

    Gson gson = null;
    public AllUser(){
        gson = new GsonBuilder().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String list = UserList.getInstance().toJSON();
        System.out.println(list);
        if (list != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = list.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
