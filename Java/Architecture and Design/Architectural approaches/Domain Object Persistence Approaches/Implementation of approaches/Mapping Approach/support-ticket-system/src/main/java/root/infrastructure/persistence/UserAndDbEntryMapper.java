package root.infrastructure.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import root.domain.User;

@Component
public class UserAndDbEntryMapper
    implements DomainObjectAndDbEntryMapper<User, UserDbEntry>
{
    @Override
    public UserDbEntry mapToDbEntry(final User user)
    {   
        if (user == null)
        {
            return null;
        }
        final UserDbEntry userDbEntry = new UserDbEntry();
        if (user.getId() != null)
        {
            userDbEntry.setId(UUID.fromString(user.getId()));
        } 
        userDbEntry.setLogin(user.getLogin());
        userDbEntry.setPassword(user.getPassword());
        userDbEntry.setRole(user.getRole());
        return userDbEntry;
    }
    
    @Override
    public User mapToDomainObject(final UserDbEntry userDbEntry)
    {   
        if (userDbEntry == null)
        {
            return null;
        }
        return new User.Builder(
                userDbEntry.getLogin(), userDbEntry.getPassword(), userDbEntry.getRole())
            .id(userDbEntry.getId().toString())
            .build();
    }
}
