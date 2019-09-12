package ssp.serviceprovider.infrastructure.persistence.serviceuser;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "service_user")
@Access(AccessType.FIELD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class ServiceUserDbEntry
{
    @Id
    @NonNull
    private UUID id;
    
    @NonNull
    private String login;
    
    @NonNull
    private String password;
    
    @NonNull
    private String serverId;
}
