package root.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UseCaseRequestValidationResult 
{
    private Collection<String> report;

    public UseCaseRequestValidationResult() 
    {
        this.report = new ArrayList<>();
    }

    public boolean isSuccessful() 
    {
        return report.isEmpty();
    }
    
    public void addValidationError(String errorDescription)
    {
        report.add(errorDescription);
    }
    
    public Collection<String> getValidationReport()
    {
        return Collections.unmodifiableCollection(report);
    }
}
