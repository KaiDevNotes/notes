package ssp.serviceprovider.domain.server;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Location
{
    USA_NEW_YORK("USA", "New York", "US"),
    NORWAY_OSLO("Norway", "Oslo", "NO"),
    JAPAN_TOKYO("Japan", "Tokio", "JP");

    private final String country;
    private final String city;
    private final String iso2Code;   
}
