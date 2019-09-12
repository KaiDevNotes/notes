package ssp.serviceprovider.domain.serviceuser;

import lombok.Value;
import ssp.common.domain.Identity;

@Value
public class ServiceUserId implements Identity
{
    private final String value;
}
