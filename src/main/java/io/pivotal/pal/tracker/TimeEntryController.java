package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {


        TimeEntry tE = timeEntryRepository.create(timeEntryToCreate);
        ResponseEntity<TimeEntry> result = new ResponseEntity<>(tE, HttpStatus.CREATED);
        return result;

    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry tE = timeEntryRepository.find(timeEntryId);
        if (tE != null)
            return new ResponseEntity<TimeEntry>(tE, HttpStatus.OK);
        else return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> teList = timeEntryRepository.list();
        // Arrays.asList(teList);
        ResponseEntity<List<TimeEntry>> result = new ResponseEntity<>(teList, HttpStatus.OK);
        return result;
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry tE = timeEntryRepository.update(timeEntryId, expected);
        if (tE != null)
            return new ResponseEntity<TimeEntry>(tE, HttpStatus.OK);
        else return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
