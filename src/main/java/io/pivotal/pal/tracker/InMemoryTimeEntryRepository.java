package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.*;

import static java.util.Arrays.asList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private HashMap<Long, TimeEntry> map = new HashMap<Long, TimeEntry>();
    private long idVal = 0L;

    public TimeEntry find(long id) {

        TimeEntry timeEntry = map.get(id);

        return timeEntry;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        ++idVal;
        TimeEntry tE = new TimeEntry(idVal, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        map.put(idVal, tE);

        return find(idVal);
    }

    public List<TimeEntry> list() {

        Collection<TimeEntry> values = map.values();
        List<TimeEntry> listOfValues = new ArrayList<TimeEntry>(values);

        return listOfValues;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (map.containsKey(id)) {

            TimeEntry tE = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
            map.put(id, tE);

            return find(id);
        }
        return null;
    }

    public void delete(long id) {

        Iterator<Long> myIter = map.keySet().iterator();
        System.out.println("isEmpty == " + map.keySet().isEmpty());
        while (myIter.hasNext()) {
            Long idKey = myIter.next();
            if (idKey == id) {
                myIter.remove();
                System.out.println("isEmpty == " + map.keySet().isEmpty());
            }
            System.out.println("chk hasNext");
        }

    }
}
