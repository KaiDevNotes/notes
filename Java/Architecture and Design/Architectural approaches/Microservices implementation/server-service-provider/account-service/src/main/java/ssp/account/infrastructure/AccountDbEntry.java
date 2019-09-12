package ssp.account.infrastructure;

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
import ssp.account.domain.Account;

@Entity
@Table(name = "account")
@Access(AccessType.FIELD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class AccountDbEntry
{
    @Id
    @NonNull
    private UUID id;
    
    @NonNull
    private String email;
    
    @NonNull
    private String password; 
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private Account.Role role;
}
