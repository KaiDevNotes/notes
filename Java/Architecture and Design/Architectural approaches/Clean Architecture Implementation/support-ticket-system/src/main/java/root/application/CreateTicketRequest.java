package root.application;

import root.application.validation.DomainObjectId;
import root.application.validation.Length;

public class CreateTicketRequest implements UseCaseRequest
{
    @DomainObjectId(errorMessage = "Submitter Id is invalid.")
    private String submitterId;
    
    @Length(min = 1, max = 128,
        errorMessage = "Issue description should not be empty or longer than 128 characters.")
    private String issueDescription;

    public String getSubmitterId() 
    {
        return submitterId;
    }

    public void setSubmitterId(final String submitterId) 
    {
        this.submitterId = submitterId;
    }

    public String getIssueDescription() 
    {
        return issueDescription;
    }

    public void setIssueDescription(final String issueDescription) 
    {
        this.issueDescription = issueDescription;
    }
}
