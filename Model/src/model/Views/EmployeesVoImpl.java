package model.Views;

import model.Views.common.EmployeesVo;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 18 18:06:52 AST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeesVoImpl extends ViewObjectImpl implements EmployeesVo {
    /**
     * This is the default constructor (do not remove).
     */
    public EmployeesVoImpl() {
    }

    /**
     * Returns the variable value for deptIdVar.
     * @return variable value for deptIdVar
     */
    public Integer getdeptIdVar() {
        return (Integer) ensureVariableManager().getVariableValue("deptIdVar");
    }

    /**
     * Sets <code>value</code> for variable deptIdVar.
     * @param value value to bind as deptIdVar
     */
    public void setdeptIdVar(Integer value) {
        ensureVariableManager().setVariableValue("deptIdVar", value);
    }
}

