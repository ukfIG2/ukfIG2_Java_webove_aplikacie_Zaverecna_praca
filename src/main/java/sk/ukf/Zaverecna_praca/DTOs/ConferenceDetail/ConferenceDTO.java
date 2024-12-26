package sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConferenceDTO {
    private String nameOfConference;
    private LocalDate dateOfConference;
    private String stateOfConference;
    private List<StageDTO> stages;

    public String getNameOfConference() {
        return nameOfConference;
    }

    public void setNameOfConference(String nameOfConference) {
        this.nameOfConference = nameOfConference;
    }

    public LocalDate getDateOfConference() {
        return dateOfConference;
    }

    public void setDateOfConference(LocalDate dateOfConference) {
        this.dateOfConference = dateOfConference;
    }

    public String getStateOfConference() {
        return stateOfConference;
    }

    public void setStateOfConference(String stateOfConference) {
        this.stateOfConference = stateOfConference;
    }

    public List<StageDTO> getStages() {
        return stages;
    }

    public void setStages(List<StageDTO> stages) {
        this.stages = stages;
    }

    public ConferenceDTO(String nameOfConference, LocalDate dateOfConference, String stateOfConference, List<StageDTO> stages) {
        this.nameOfConference = nameOfConference;
        this.dateOfConference = dateOfConference;
        this.stateOfConference = stateOfConference;
        this.stages = stages;
    }

    public ConferenceDTO() {

    }
}
