package utils;

import java.util.List;

public class DeleteEmployeeRequest {
    private List<Integer> ids;

    public DeleteEmployeeRequest(int empNumber) {
        this.ids = List.of(empNumber);
    }

    public List<Integer> getIds() {
        return ids;
    }
}
