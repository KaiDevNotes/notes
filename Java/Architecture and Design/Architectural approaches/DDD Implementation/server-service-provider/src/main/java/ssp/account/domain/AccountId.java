package ssp.account.domain;

import lombok.Value;
import ssp.common.domain.Identity;

@Value
public class AccountId implements Identity
{
    private final String value;
}
