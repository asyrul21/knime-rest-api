package com.webapi.knime_api_service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("workflow")
public class WorkflowResource {
	
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	// http://localhost:8080/knime-api-service/api/workflow
    public String getAvailability() {
        return "Workflow resource is available!";
    }
	
	@GET
	@Path("info")
    @Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/knime-api-service/api/workflow/info
    public WorkflowInformation getInformation() {
		
		WorkflowInformation wi = new WorkflowInformation();
		
		wi.setFileType("csv");
		wi.setColumnFilter("Country");
		wi.setRowFilter("United Kingdom");
		wi.setOutputFormat("csv");
		
		
        return wi;
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/knime-api-service/api/workflow/
	/*  Make sure to set you 1) Request Body (RAW) :
	 * {
		    "columnFilter": "Country",
		    "fileType": "csv",
		    "outputFormat": "csv",
		    "rowFilter": "United Kingdom"
		}                            
	
	* 2) set your headers properties (Content-Type and Accept) both to application/json
	*
	*/
	
    public WorkflowInformation sendInformation(WorkflowInformation wi) {
		
		System.out.println("A POST request submitted with Raw Request Body:");
		System.out.println(wi);
        return wi;
    }
}
