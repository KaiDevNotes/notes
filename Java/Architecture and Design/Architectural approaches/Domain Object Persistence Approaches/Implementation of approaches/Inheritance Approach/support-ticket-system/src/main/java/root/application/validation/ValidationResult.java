package root.application.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ValidationResult
{
    private final Collection<String> report;

    public ValidationResult() 
    {
        this.report = new ArrayList<>();
    }

    public boolean isSuccessful() 
    {
        return report.isEmpty();
    }
    
    public void addValidationError(final String errorDescription)
    {
        report.add(errorDescription);
    }
    
    public Collection<String> getValidationReport()
    {
        return Collections.unmodifiableCollection(report);
    }
}
