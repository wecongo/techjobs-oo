package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.Employer;
import org.launchcode.models.Location;
import org.launchcode.models.CoreCompetency;
import org.launchcode.models.PositionType;
import org.launchcode.models.forms.JobForm;

import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, /*int id*/ @RequestParam int id) {

        // TODO #1 - CHECK - get the Job with the given ID and pass it into the view


        Job jobs = jobData.findById(id);

        //model.addAttribute("title", "Jobs with " + column.getName() + ": " + name);
        model.addAttribute("jobs", jobs);

        if (jobs!=null) {
            return "job-detail";
        }
        else{
            return ("No Jobs here");
        }
    }



    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("jobForm",new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.

        if (errors.hasErrors()) {
            model.addAttribute("title", "We Have Errors");
            model.addAttribute("jobForm", jobForm);
            return "new-job";
       }
        if (!jobForm.getName().equals("")&&!errors.hasErrors()) {
// jobForm data checks out until here
            Job toAdd = jobForm.convertToJob();  //




  //convertToJob is return some random pointers to diff

            jobData.add(toAdd);

        }

        // code block find size of Job Area to Display Last job w/ redirect
        ArrayList<Job> allJobs = jobData.findAll();
        int lastJob = allJobs.size();
        String lastJobString = String.valueOf(lastJob);


        return "redirect:/job?id="+lastJob;

    }
}
