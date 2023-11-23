package persistence;

import org.json.JSONObject;

// The Writable interface represents an object that can be converted to a JSON object for serialization.
// Classes that implement this interface must provide an implementation for the "toJson" method.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
