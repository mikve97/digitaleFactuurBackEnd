package nl.dfbackend.git.services;

import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.models.TripModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    public ProjectService(){}
    private String jsonProject = "";

    private List<TripModel> fetchAllTripsWithProject(){
        TripService ts = new TripService();
        List<TripModel> projects = ts.fetchAllTripsWithProject();

        return projects;
    }

    private List<TripModel> fetchAllTripsByProject(int projectId){
        TripService ts = new TripService();
        List<TripModel> projects = ts.fetchAllTripsByProject(projectId);

        return projects;
    }

    public String getJsonProject() {
        return jsonProject;
    }

    public void setJsonProject(String jsonProject) {
        this.jsonProject = jsonProject;
    }

    public List<ProjectModel> getProjectsFromApi(String apiKey, String userId){
        InputStream apiResult = httpRequest("https://administratie.digitalefactuur.nl/api/"+userId+"/"+apiKey+"/uren_get_projects_klanten", "GET");
        List<ProjectModel> projects = parseXML(apiResult);

        return projects;
    }

    /**
     * Simple method to create a HTTP request
     * @author Mike van Es
     * @param url
     * @return InpputStream
     */

    public InputStream httpRequest(String url, String requestType){
        try {

            //The URL wich we are going to send to.
            URL requestUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
            System.out.println(requestType);
            //The request type, POST, GET etc.
            con.setRequestMethod(requestType);
            con.setRequestProperty("Content-Type", "application/json");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            //The response wich we recieve e.g. 200, 400, 404
            int status = con.getResponseCode();
            InputStream inputstream = con.getInputStream();

            return inputstream;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * Parses the given XML file from Digitalefactuur. Returns a list of projects with trips.
     * @author Mike van Es
     * @param stream
     * @return List<ProjectModel>
     */
    public List<ProjectModel> parseXML(InputStream stream ) {

        try {
            Document doc = createDocumentFromXML(stream);
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("project");
            List<ProjectModel> projectList = new ArrayList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int projectId = Integer.parseInt( eElement.getElementsByTagName("project_id").item(0).getTextContent() );
                    String projectName = eElement.getElementsByTagName("project_name").item(0).getTextContent();
                    List<TripModel> tripsFromProject = this.fetchAllTripsByProject(projectId);
                    //Create new projectModel from trip
                    ProjectModel tempProjectModel = new ProjectModel(projectId, projectName, tripsFromProject);
                    projectList.add(tempProjectModel);
                }
            }

            return projectList;
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan");
            e.printStackTrace();
        }

        return null;
    }


    public Document createDocumentFromXML(InputStream apiResult) throws Exception
    {
        try {
            //Example code to read new the results
            BufferedReader in = new BufferedReader(new InputStreamReader(apiResult));
            String inputLine;
            String fullXml = "";
            while ((inputLine = in.readLine()) != null) {
                fullXml = fullXml + inputLine;
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(fullXml));
            return builder.parse(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
