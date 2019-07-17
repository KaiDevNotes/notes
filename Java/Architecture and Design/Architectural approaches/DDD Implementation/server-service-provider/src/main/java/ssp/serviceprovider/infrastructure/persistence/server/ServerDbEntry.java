package ssp.serviceprovider.infrastructure.persistence.server;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ssp.serviceprovider.domain.server.Location;
import ssp.serviceprovider.domain.server.Server;

@Entity
@Table(name = "server")
@Access(AccessType.FIELD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class ServerDbEntry
{
    @Id
    @NonNull
    private UUID id;
    
    @NonNull
    private String ip;
    
    @NonNull
    private String port;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private Location location;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private Server.Status status;
}
