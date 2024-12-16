package sk.ukf.Zaverecna_praca;

public enum Role {
    ADMIN("ROLE_admin"),
    REGISTERED_VISITOR("ROLE_registered_visitor"),
    SPEAKER("ROLE_speaker");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
