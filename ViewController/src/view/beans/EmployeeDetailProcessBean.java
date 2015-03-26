package view.beans;

import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class EmployeeDetailProcessBean {

    Integer departmentIdParam = null;

    public void setDepartmentIdParam(Integer departmentIdParam) {
        this.departmentIdParam = departmentIdParam;
    }

    public Integer getDepartmentIdParam() {
        return departmentIdParam;
    }


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String CommitAction() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        System.out.println("Before executing of Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
}
