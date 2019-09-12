package ssp.serviceprovider.presentation.dto;

import javax.validation.constraints.Size;

import lombok.Value;

@Value
public class MoveToAnotherServerRequest
{
    @Size(min = 36, max = 36)
    private final String currentServerId;
    @Size(min = 36, max = 36)
    private final String targetServerId;
}
