package erp.core;

import java.io.Serializable;

/** The system date. The unit is the day, and it starts at day 1. */
class Date implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    //attributes
    private int _currentDate = 1;

    //getter for the current date
    int getCurrentDate() { 
        return _currentDate; 
    }

    //increment the current date
    void advanceDate() { 
        _currentDate++; 
    }
}