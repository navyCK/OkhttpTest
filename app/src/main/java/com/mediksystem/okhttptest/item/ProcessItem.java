package com.mediksystem.okhttptest.item;

public class ProcessItem {
    private int SubUniquenumber;
    private int ProcessSubTypeCodenumber;
    private String ProcessSubType;
    private String BeginTime;
    private String FinishTime;
    private String SpecificationRepresentation;
    private int ProcessUniquenumber;
    private String RegistrationTime;
    private String ModificationTime;
    private boolean DeleteYesorno;

    public ProcessItem() {
        this.SubUniquenumber = SubUniquenumber;
        this.ProcessSubTypeCodenumber = ProcessSubTypeCodenumber;
        this.ProcessSubType = ProcessSubType;
        this.BeginTime = BeginTime;
        this.FinishTime = FinishTime;
        this.SpecificationRepresentation = SpecificationRepresentation;
        this.ProcessUniquenumber = ProcessUniquenumber;
        this.RegistrationTime = RegistrationTime;
        this.ModificationTime = ModificationTime;
        this.DeleteYesorno = DeleteYesorno;
    }

    public void setSubUniquenumber(int subUniquenumber) {
        this.SubUniquenumber = subUniquenumber;
    }
    public void setProcessSubTypeCodenumber(int processSubTypeCodenumber) {
        this.ProcessSubTypeCodenumber = processSubTypeCodenumber;
    }
    public void setProcessSubType(String processSubType) {
        this.ProcessSubType = processSubType;
    }
    public void setBeginTime(String beginTime) {
        this.BeginTime = beginTime;
    }
    public void setFinishTime(String finishTime) {
        this.FinishTime = finishTime;
    }
    public void setSpecificationRepresentation(String specificationRepresentation) {
        this.SpecificationRepresentation = specificationRepresentation;
    }
    public void setProcessUniquenumber(int processUniquenumber) {
        this.ProcessUniquenumber = processUniquenumber;
    }
    public void setRegistrationTime(String registrationTime) {
        this.RegistrationTime = registrationTime;
    }
    public void setModificationTime(String modificationTime) {
        this.ModificationTime = modificationTime;
    }
    public void setDeleteYesorno(boolean deleteYesorno) {
        this.DeleteYesorno = deleteYesorno;
    }

    public int getSubUniquenumber() {
        return SubUniquenumber;
    }
    public int getProcessSubTypeCodenumber() {
        return ProcessSubTypeCodenumber;
    }
    public String getProcessSubType() {
        return ProcessSubType;
    }
    public String getBeginTime() {
        return BeginTime;
    }
    public String getFinishTime() {
        return FinishTime;
    }
    public String getSpecificationRepresentation() {
        return SpecificationRepresentation;
    }
    public int getProcessUniquenumber() {
        return ProcessUniquenumber;
    }
    public String getRegistrationTime() {
        return RegistrationTime;
    }
    public String getModificationTime() {
        return ModificationTime;
    }
    public boolean getDeleteYesorno() {
        return DeleteYesorno;
    }


}
