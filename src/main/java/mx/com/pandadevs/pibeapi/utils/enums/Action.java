package mx.com.pandadevs.pibeapi.utils.enums;

public enum Action {
    Creacion("Creación"),
    Actualizacion("Actualización"),
    elminacion("Eliminación");
    private String action;

    Action(String action) {
    }
    // getters && setters

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
