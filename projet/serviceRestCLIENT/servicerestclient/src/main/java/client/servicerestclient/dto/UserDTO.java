package client.servicerestclient.dto;

public class UserDTO {
    private int id;

    public UserDTO() {}

    public UserDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
