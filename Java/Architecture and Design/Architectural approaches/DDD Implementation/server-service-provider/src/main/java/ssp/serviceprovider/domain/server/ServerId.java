package ssp.serviceprovider.domain.server;

import lombok.Value;
import ssp.common.domain.Identity;

@Value
public class ServerId implements Identity
{
    private final String value;
}
