package utils;

public class EmployeeRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String empPicture;

    public EmployeeRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.middleName = "";
        this.empPicture = null;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmpPicture() {
        return empPicture;
    }
}
