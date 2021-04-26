package sep.tinee.client;

/**
 * Representation of all the valid states for the client.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public enum ClientState {
    // A value for each state along with its corresponding number.
    DONE(0), MAIN(1), DRAFT(2);
    
    // The state integer.
    private int state;
    
    /**
     * Initialise with the corresponding state.
     * @param state The integer state
     */
    ClientState(int state) {
        this.state = state;
    }
    
    /**
     * Sets the current state.
     * @param state the current state
     */
    public void setState(int state) {
        this.state = state;
    }
    
    /**
     * Return the current state.
     * @return the current state
     */
    public int getState() {
        return this.state;
    }
}
