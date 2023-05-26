import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class NewServlet extends HttpServlet {

   
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
        PrintWriter pw = response.getWriter();
        
        String firstname = request.getParameter("firstname");
        String email = request.getParameter("email");
        String feedback = request.getParameter("feedback");
        
        
       
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection aa = DriverManager.getConnection("jdbc:derby://localhost:1527/feedback", "root", "root");
            pw.println("connection done");
            Statement abc = aa.createStatement();
           
            
             abc.executeUpdate("INSERT INTO ROOT.FB (FIRSTNAME, EMAIL, FEEDBACK) VALUES ('"+firstname+"','" +email+",'"+feedback+"')");
        pw.println("stored");
         
        }
        catch(Exception e){
            System.out.println(e);
            pw.println(e);
        }
    }

 
}
