    package sk.ukf.Zaverecna_praca.dto;

    import sk.ukf.Zaverecna_praca.StateOfConference;

    import java.time.LocalDate;
    import java.time.LocalDateTime;

    public class ConferenceDTO {
        private Long id;
        private String nameOfConference;
        private LocalDate dateOfConference;
        private StateOfConference stateOfConference;
        private String comment;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

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

        public StateOfConference getStateOfConference() {
            return stateOfConference;
        }

        public void setStateOfConference(StateOfConference stateOfConference) {
            this.stateOfConference = stateOfConference;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        public ConferenceDTO(Long id, String nameOfConference, LocalDate dateOfConference, StateOfConference stateOfConference, String comment, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.nameOfConference = nameOfConference;
            this.dateOfConference = dateOfConference;
            this.stateOfConference = stateOfConference;
            this.comment = comment;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }


    }
