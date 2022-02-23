package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);
    Iterable<Venue> findByCapacityLessThanEqual(int isSize);
    Iterable<Venue> findByCapacityIsBetween(int isSize, int isSize2);
    Iterable<Venue> findByCapacityIsGreaterThan(int isSize);
}
