package sk.ukf.Zaverecna_praca;

public enum StateOfConference {
    PREPARING("prepearing"),
    REGISTERING("registering"),
    REGISTERING_ENDED("registering_ended"),
    ENDED("ended");

    private final String state;

    StateOfConference(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

