package sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail;

import java.util.List;

public class StageDTO {
    private String nameOfStage;
    private List<PresentationDTO> presentations;

    public String getNameOfStage() {
        return nameOfStage;
    }

    public void setNameOfStage(String nameOfStage) {
        this.nameOfStage = nameOfStage;
    }

    public List<PresentationDTO> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<PresentationDTO> presentations) {
        this.presentations = presentations;
    }

    public StageDTO(String nameOfStage, List<PresentationDTO> presentations) {
        this.nameOfStage = nameOfStage;
        this.presentations = presentations;
    }
}
