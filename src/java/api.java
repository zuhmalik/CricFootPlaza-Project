
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class api extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "http://mapps.cricbuzz.com/cbzios/match/livematches";

        PrintWriter out = response.getWriter();

        try {

            System.out.println("url" + url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            System.out.println("123");

            int responseCode = con.getResponseCode();
            System.out.println(responseCode);

            // con.setRequestProperty("x-rapidapi-key", "a1c5a03b45msh57a028e40b2954ap14cd55jsn3c5f8e0e1841");
            System.out.println("2");
            System.out.println("\n sending 'GET' request to URL :" + url);
            System.out.println("Response code :" + responseCode);

//            InputStreamReader in =new InputStreamReader(obj.openStream());  
//            JSONParser parser = new JSONParser();
//            JSONObject json = ( JSONObject ) parser.parse(in);
//            String rates = json.get("title").toString();
            // System.out.println("Total items"+rates);
            //      String gbp = rates.get("GBP"). toString ();
            try (Scanner scanner = new Scanner(obj.openStream())) {
                String qwe = "";

                while (scanner.hasNext()) {
                    qwe += scanner.nextLine();
                }
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(qwe);
                // System.out.println("json a string" + json.toString());
                JSONArray array = (JSONArray) json.get("matches");
                //out.println(array.toString());
                for (int a = 0; a < array.size(); a++) {

                    JSONObject obj1 = (JSONObject) array.get(a);
                    // out.println(obj1.toString()+"<br>"+"<br>"+"<br>");
                    JSONObject obj2 = (JSONObject) obj1.get("bow_team");
                    JSONArray arrayINGS1 = (JSONArray) obj2.get("innings");
                    String series_name = obj1.get("series_name").toString();
                    //  String location_name= obj1.get("location").toString();
                    String score2 = "";
                    String overs2 = "";
                    String wkts2 = "";
                    String overs = "";
                    String wkts = "";
                    String score = "";
                    String team1 = "";
                    JSONObject obj4 = (JSONObject) obj1.get("bat_team");
                    JSONObject obj6 = (JSONObject) obj1.get("header");
                    String status = obj6.get("status").toString();
                    JSONObject obj7 = (JSONObject) obj1.get("venue");
                    String name = obj7.get("name").toString();
                    String location = obj7.get("location").toString();
                    

                    JSONArray arrayINGS2 = (JSONArray) obj4.get("innings");
                    if (arrayINGS1.size() > 0 && arrayINGS2.size() > 0) {
                        JSONObject obj3 = (JSONObject) arrayINGS1.get(0);
                        score = obj3.get("score").toString();
                        wkts = obj3.get("wkts").toString();
                        overs = obj3.get("overs").toString();

                        JSONObject array3 = (JSONObject) obj1.get("team1");
                        team1 = array3.get("name").toString();

                        JSONObject obj5 = (JSONObject) arrayINGS2.get(0);
                        score2 = obj5.get("score").toString();
                        wkts2 = obj5.get("wkts").toString();
                        overs2 = obj5.get("overs").toString();
                        

                    }

                    JSONObject array4 = (JSONObject) obj1.get("team2");
                    String team2 = array4.get("name").toString();
//                out.println("<br>" + series_name + "<br>" + team1 + "<br>" + "Score" + score + "/" + wkts + "<br>" + "Overs" + overs + "<br>" + team2 + "<br>" + "Score" + score2 + "/" + wkts2 + "<br>" + "Overs" + overs2 +
//                "<br><br>");
                    out.println("<!DOCTYPE html>\n"
                            + "<!--\n"
                            + "To change this license header, choose License Headers in Project Properties.\n"
                            + "To change this template file, choose Tools | Templates\n"
                            + "and open the template in the editor.\n"
                           
                            + "-->\n"
                            + "<html>\n"
                            + "    <head>\n"
                            + "        <title>Live Cricket Scores</title>\n"
                            + "        <meta charset=\"UTF-8\">\n"
                            + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                            + "        <link rel=\"stylesheet\" href=\"lin.css\">\n"
                             +"<style>\n" +

"body {\n" +
"  font-family: \"Lucida Console\", \"Courier New\", monospace;\n" +
"}\n" +
"</style>"
                            + "        \n"
                            + "    </head>\n"
                            + "    <body onload=\"api\" >\n"
                            + "        <div class=\"okk\">\n"
                            + "            \n"
                            + "            <h2>Cricket Matches</h2>\n"
                            + "\n"
                            + "            <ul>\n"
                            + "                <li>" + "<br>" + "Series: " + series_name + "<br><br>" + "Venue: " + name + " " + location + "<br><br>" + "Team 1: " + team1 + "<br>" + "Score: " + score + "/" + wkts + "<br>" + "Overs: " + overs + "<br><br>" + "Team 2: " + team2 + "<br>" + "Score: " + score2 + "/" + wkts2 + "<br>" + "Overs: " + overs2 + "<br><br>" + "Result: " + status + "<br><br>" 
                            + "</li>\n"
                            + "            </ul>\n"
                            + "        </div>\n"
                            + "         \n"
                            + " \n"
                            + "    </body>\n"
                            + "</html>\n"
                            + "");

                    //getting info from the bat-team stuff okkkk
                    //JSONObject obj3 = (JSONObject)obj2.get("bat_team");
                    // JSONObject obj4 = (JSONObject)obj3.get("innings");
//                    String score= obj3.get("score").toString();
//                    String wkts= obj3.get("wkts").toString();
//                    String overs= obj3.get("overs").toString();
//                    
//                    //getting second teams score and result
//                    JSONObject obj5 = (JSONObject)obj2.get("bow_team");
//                    JSONObject obj6 = (JSONObject)obj5.get("innings");
//                    String score1= obj5.get("score").toString();
//                    String wkts1= obj5.get("wkts").toString();
//                    String overs1= obj5.get("overs").toString();
//                    
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String url = "http://mapps.cricbuzz.com/cbzios/match/livematches";
//
//        PrintWriter out = response.getWriter();
//
//        try {
//           
//            System.out.println("url" + url);
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//            con.setRequestMethod("GET");
//            System.out.println("123");
//
//            int responseCode = con.getResponseCode();
//            System.out.println(responseCode);
//
//            // con.setRequestProperty("x-rapidapi-key", "a1c5a03b45msh57a028e40b2954ap14cd55jsn3c5f8e0e1841");
//            System.out.println("2");
//            System.out.println("\n sending 'GET' request to URL :" + url);
//            System.out.println("Response code :" + responseCode);
//
////            InputStreamReader in =new InputStreamReader(obj.openStream());  
////            JSONParser parser = new JSONParser();
////            JSONObject json = ( JSONObject ) parser.parse(in);
////            String rates = json.get("title").toString();
//            // System.out.println("Total items"+rates);
//            //      String gbp = rates.get("GBP"). toString ();
//            try (Scanner scanner = new Scanner(obj.openStream())) {
//                String qwe = "";
//
//                
//                while (scanner.hasNext()) {
//                    qwe += scanner.nextLine();
//                }
//                JSONParser parser = new JSONParser();
//                JSONObject json = (JSONObject) parser.parse(qwe);
//                System.out.println("json a string" + json.toString());
//                JSONArray array = (JSONArray) json.get("matches");
//                
//                for (int a = 0; a < array.size(); a++) {
//
//                    JSONObject obj1 = (JSONObject) array.get(a);
//                    System.out.println(obj1.toString());
//                    JSONObject obj2 = (JSONObject) obj1.get("series_name");
//                    System.out.println(obj2.toString());
//                    
//                    //getting info from the bat-team stuff okkkk
//                    JSONObject obj3 = (JSONObject)obj2.get("bat_team");
//                    JSONObject obj4 = (JSONObject)obj3.get("innings");
//                    String score= obj3.get("score").toString();
//                    String wkts= obj3.get("wkts").toString();
//                    String overs= obj3.get("overs").toString();
//                    
//                    //getting second teams score and result
//                    JSONObject obj5 = (JSONObject)obj2.get("bow_team");
//                    JSONObject obj6 = (JSONObject)obj5.get("innings");
//                    String score1= obj5.get("score").toString();
//                    String wkts1= obj5.get("wkts").toString();
//                    String overs1= obj5.get("overs").toString();
//                    
//                    
//                    
//                    
//                    
//                    
//                }
//            }
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//
//        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
