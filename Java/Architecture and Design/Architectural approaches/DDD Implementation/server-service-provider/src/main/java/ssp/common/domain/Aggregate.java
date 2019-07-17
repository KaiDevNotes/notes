package ssp.common.domain;

public interface Aggregate<ID extends Identity>
{
    ID getId();
}
