package nl.dfbackend.git.unittests;

import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.services.ProjectService;
import nl.dfbackend.git.services.TripService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author Mike van Es
 */
public class ProjectTest {

    public boolean equals(Object o){
        if(getClass() == o.getClass()){
            return true;
        }
        else{
            return false;}
    }

    @Test
    public void getProjectsTest() throws SQLException {
        String userid = "56840";
        String apikey = "79554e2460ae336c07c3eb0208adbb4cc4af184c17b51e0a2373cc0f9bba87b5";

        ProjectService ps = new ProjectService();
        List<ProjectModel> resultList = ps.getProjectsFromApi(apikey, userid );

        for(ProjectModel result: resultList){
            assertEquals(result.getClass(), ProjectModel.class);
        }
    }

}
