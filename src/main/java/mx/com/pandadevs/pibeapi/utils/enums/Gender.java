package mx.com.pandadevs.pibeapi.utils.enums;

public enum Gender {
    Hombre("Hombre"),
    Mujer("Mujer"),
    Otro("Otro");
    private String gender;
    Gender(String hombre) {
    }
    // Getters && Setters

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
