package org.launchcode.models.forms;

import org.launchcode.models.CoreCompetency;
import org.launchcode.models.Employer;
import org.launchcode.models.Location;
import org.launchcode.models.PositionType;
import org.launchcode.models.data.JobData;
import org.launchcode.models.Job;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class JobForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;

    @NotNull
    private int employerId;

    @NotNull
    private int locationId;

    @NotNull
    private int coreCompetencyId;

    @NotNull
    private int positionTypeId;

    /*
        TODO #3 - Check? -    Included other fields needed to create a job,
        with correct validation attributes and display names.
        Don't forget to add getters and setters

     */

    private ArrayList<Employer> employers;
    private ArrayList<Location> locations;
    private ArrayList<CoreCompetency> coreCompetencies;
    private ArrayList<PositionType> positionTypes;

    public JobForm() {

        JobData jobData = JobData.getInstance();

        /*
            TODO #4 -  populate the other ArrayList collections needed in the view
        */

        employers = jobData.getEmployers().findAll();
        locations = jobData.getLocations().findAll();
        coreCompetencies = jobData.getCoreCompetencies().findAll();
        positionTypes = jobData.getPositionTypes().findAll();

    }

    public Job convertToJob(){

        return new Job(this.name,
                this.getEmployer(),
                this.getLocation(),
                this.getPositionType(),
                this.getCoreCompetency()
        );

    }

    //getters for returning the Job that was created.
  // need to get by employeer ID not position in array

    public Employer getEmployer(){

        for(int i = 0; i<=employers.size(); i++)
            if (employers.get(i).getId()==this.employerId) {
                return employers.get(i);
            }

      return null;

    }

    public Location getLocation(){
        for(int i = 0; i<=locations.size(); i++)
            if (locations.get(i).getId()==this.locationId) {
                return locations.get(i);
            }

        return null;
    }

    public PositionType getPositionType(){
        for(int i = 0; i<=positionTypes.size(); i++)
            if (positionTypes.get(i).getId()==this.positionTypeId) {
                return positionTypes.get(i);
            }

        return null;
    }

    public CoreCompetency getCoreCompetency(){
        for(int i = 0; i<=coreCompetencies.size(); i++)
            if (coreCompetencies.get(i).getId()==this.coreCompetencyId) {
                return coreCompetencies.get(i);
            }

        return null;
    }

//Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //Employers
    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }


    public ArrayList<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(ArrayList<Employer> employers) {
        this.employers = employers;
    }


//Location
    public int getLocationId() {
    return locationId;
}

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }


//skill
    public int getCoreCompetencyId() {
    return coreCompetencyId;
}

    public void setCoreCompetencyId(int coreCompetencyId) {
        this.coreCompetencyId = coreCompetencyId;
    }

    public ArrayList<CoreCompetency> getCoreCompetencies() {
        return coreCompetencies;
    }

    public void setCoreCompetencies(ArrayList<CoreCompetency> coreCompetencies) {
        this.coreCompetencies = coreCompetencies;
    }

//PositionType
public int getPositionTypeId() {
    return positionTypeId;
}

    public void setPositionTypeId(int positionTypeId) {
        this.positionTypeId = positionTypeId;
    }

    public ArrayList<PositionType> getPositionTypes() {
        return positionTypes;
    }

    public void setPositionTypes(ArrayList<PositionType> positionTypes) {
        this.positionTypes = positionTypes;
    }
}
