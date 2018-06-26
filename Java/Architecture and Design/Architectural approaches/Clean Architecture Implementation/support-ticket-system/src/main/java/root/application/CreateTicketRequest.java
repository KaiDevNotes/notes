package root.application;

public class CreateTicketRequest implements UseCaseRequest
{
    private String submitterId;
    private String issueDescription;

    public String getSubmitterId() 
    {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) 
    {
        this.submitterId = submitterId;
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
