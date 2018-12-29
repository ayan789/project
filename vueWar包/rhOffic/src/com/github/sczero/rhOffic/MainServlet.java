package com.github.sczero.rhOffic;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet
{
  private static final long serialVersionUID = 547134944883862893L;

  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String uri = request.getRequestURI();

    List<String> list = Arrays.asList(new String[] { "js", "css", "png", "jpg" });
    boolean isHTML = true;
    String contentType = "";
    for (String type : list) {
      if (uri.endsWith(type)) {
        isHTML = false;
        contentType = type;
      }
    }
    response.setCharacterEncoding("UTF-8");
    response.setStatus(200);
    if (isHTML) {
      response.setContentType("text/html;charset=UTF-8");
      response.getOutputStream().write(Files.readAllBytes(Paths.get(request.getRealPath("/WEB-INF/index.html"), new String[0])));
    } else {
      if (contentType.equals("js")) {
        response.setContentType("text/javascript;charset=UTF-8");
      }
      if (contentType.equals("css")) {
        response.setContentType("text/css;charset=UTF-8");
      }
      if (contentType.equals("png")) {
        response.setContentType("image/png;charset=UTF-8");
      }
      if (contentType.equals("jpg")) {
        response.setContentType("image/jpeg;charset=UTF-8");
      }
      String staticUri = uri.substring(uri.indexOf("/static"));
      response.getOutputStream()
        .write(Files.readAllBytes(Paths.get(request.getRealPath("/WEB-INF" + staticUri), new String[0])));
    }
  }
}