package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.jws.WebService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UserStatus extends HttpServlet {

    UserList userList = UserList.getInstance();

    Gson gson = null;

    public UserStatus() {
        gson = new GsonBuilder().create();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        User user = User.fromJSON(bufStr);
        if (user != null) {
            user.setStatus(user.isStatus());
            System.out.println(user.getName() + " статус изменен " + user.isStatus());
            if (userList.check(user)) {
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).equals(user)) {
                        userList.get(i).setStatus(user.isStatus());
                    }
                }
                System.out.println(user.isStatus());
            }
        } else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("login");
        String pass = req.getParameter("pass");
        User user = new User(name, pass);
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).equals(user)){
                user.setStatus(userList.get(i).isStatus());
            }
        }
        if (userList.check(user)) {
            System.out.println(user.isStatus());
        }
        System.out.println(user.toString());
        if (user != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = user.toJSON().getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;
        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
