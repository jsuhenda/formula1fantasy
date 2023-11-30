package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


// represents a log of events in the program.

public class EventLog implements Iterable<Event> {

    private static EventLog theLog;
    private Collection<Event> events;

    //EFFECTS: creates a new event log.
    private EventLog() {
        events = new ArrayList<Event>();
    }


    //EFFECTS: returns an instance of the event log and creates it if it doesn't already exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    //EFFECTS: adds an event to the event log.
    public void logEvent(Event e) {
        events.add(e);
    }

    //EFFECTS: clears the event log and logs the event.
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    //EFFECTS: returns an iterator of events.
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
