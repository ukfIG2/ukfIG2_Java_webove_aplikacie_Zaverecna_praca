package sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail;

import java.util.List;

public class StageDTO {
    private Long stageID;
    private String nameOfStage;
    private List<PresentationDTO> presentations;

    public Long getStageID() {
        return stageID;
    }

    public void setStageID(Long stageID) {
        this.stageID = stageID;
    }

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

    public StageDTO(Long id, String nameOfStage, List<PresentationDTO> presentations) {
        stageID = id;
        this.nameOfStage = nameOfStage;
        this.presentations = presentations;
    }
}
