package nl.dfbackend.git.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.models.TripModel;

/**
 * Service to handle calls too the DAO layer
 */
public class ProjectService {
    private String jsonProject = "";
    private TripService ts;
    private Map<Integer, ProjectModel> projectList = new HashMap<Integer, ProjectModel>();
    
    public ProjectService() throws SQLException {
    	ts = new TripService();
    }

    /**
     * Gets the trips based on a given projectId
     * @param projectId
     * @return List<TripModel>
     * @throws SQLException 
     */
    private List<TripModel> fetchAllTripsByProject(int projectId) throws SQLException{
        List<TripModel> projects = this.ts.fetchAllTripsByProject(projectId);
        return projects;
    }

    public ProjectModel getProjectById(int pId){
        if(this.projectList.containsKey(pId)){
            return this.projectList.get(pId);
        }else{
            return null;
        }
    }

    /**
     * Returns the projectModel in JSON format
     * @return String
     */
    public String getJsonProject() {
        return jsonProject;
    }

    /**
     * Set a projectmodel in JSON format
     * @param jsonProject
     */
    public void setJsonProject(String jsonProject) {
        this.jsonProject = jsonProject;
    }

    /**
     * Get the projects from the Digitalefactuur server
     * @param apiKey
     * @param userId
     * @return List<ProjectModel>
     */
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
                    this.projectList.put(tempProjectModel.getId(), tempProjectModel);
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

    /**
     * Creates a document file so we can use the XML response from Digitalefactuur
     * @param apiResult
     * @return Document
     * @throws Exception
     */
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
