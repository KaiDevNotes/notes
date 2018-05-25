package root.application;

import static org.apache.commons.lang3.StringUtils.isBlank;

import static root.application.ValidationHelper.isValidDomainObjectId;
import root.domain.User;

public class CreateTicketRequest 
{
    private String submitterId;
    private User submitter;
    private String issueDescription;
    
    public boolean hasErrors()
    {
        return !isValidDomainObjectId(submitterId) || 
                isBlank(issueDescription);
    }

    public String getSubmitterId() 
    {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) 
    {
        this.submitterId = submitterId;
    }

    public User getSubmitter() 
    {
        return submitter;
    }

    public void setSubmitter(User submitter) 
    {
        this.submitter = submitter;
    }

    public String getIssueDescription() 
    {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) 
    {
        this.issueDescription = issueDescription;
    }
}
