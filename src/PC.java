import lib.DomainConstraint;

import java.time.Year;

/**
 * @overview : A personal computer (PC) is described in terms of the
 *             following attributes: model, year, manufacturer, and components.
 * @attributes
 *  model           String
 *  year            Integer
 *  manufacturer    String
 *  component       Set
 *
 * @object
 *  A typical PC is <pre> v = < model, year, manufacturer, components> </pre>, where
 *  <pre>model, year, manufacturer, components</pre>
 * @abstract properties
 * mutable(model) = True /\ optional(model) = false /\ max_length(model) = 25
 * mutable(year) = false /\ optional(year) = false /\ min(year) = 1970
 * mutable(manufacturer ) = false /\ optional(manufacturer ) = false /\ max_length(manufacturer) = 50
 * mutable(components) = True /\ optional(components) = false
 *
 */
public class PC {
    @DomainConstraint(type = "String", mutable = true, optional = false, length = 25)
    private String model;

    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 1970)
    private Integer year;

    @DomainConstraint(type = "String", mutable = true, optional = false, length = 50)
    private String manufacturer;

    @DomainConstraint(type = "String", mutable = true, optional = false)
    private Set components;
    public PC(){}


    /**
     * constructor
     * @effect <pre>
     *     if pram is satisfied
     *     initialize this as PC: <model, year, manufacturer, components>
     *         else broke the process
     * </pre>
     */
    public PC(String model, String year, String manufacturer, Set components) {
        this.setModel(model);
        this.setYear(year);
        this.setManufacturer(manufacturer);
        this.setComponents(components);
    }

    /**
     * @modifies: int length /\ String content
     * @effect add length space to characters content in order to format
     */
    public String leftAlign(int length, String content){
        if (length <= content.length()){
            length = content.length() + 4;
        }
        String space = "";
        for (int i = 0; i < (length - content.length()); i++){
            space +=" ";
        }
        return content + space;
    }

    /**
     *
     * @effects <tt>return True or false when condition of year must be greater than 1970</tt>
     */
    public boolean yearValidate(String year){
        try{
            if (Integer.parseInt(year )> 1970){
                return true;
            }else {
                System.out.println("The Year can not less than 1970");
                return false;
            }
        }catch (Exception ex){
            return false;
        }

    }

    /**
     *
     * @effects <tt>return True or false when condition of model length must be greater than 25 </tt>
     */
    public boolean modelValidate(String model){
        if (model.length()<25 && model.length()>3){
            return true;

        }else {
            System.out.println("Model's length must be greater than 25!");
            return false;
        }
    }

    /**
     *
     * @effects <tt>return True or false when condition of manufacturer length must be less than 50 </tt>
     */
    public boolean manufacturerValidate(String manufacturer){
        if (manufacturer.length()<50 && manufacturer.length() >0){
            return true;
        }else {
            System.out.println("Manufacturer's length must be less than 50");
            return false;
        }
    }

    /**
     *
     * @return model
     */
    public String getModel() {
        return model;
    }


    public void setModel(String model) {
        if (modelValidate(model)){
            this.model = model;
        }
    }

    /**
     *
     * @return year
     */
    public Integer getYear() {
            return year;
    }


    /**
     *
     * @param year
     * @modifies: this.year
     * @effect: if year is valid, this object takes it, else nothing
     */
    public void setYear(String year) {
        if (yearValidate(year)) this.year =Integer.parseInt(year);
    }


    /**
     *
     * @return manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if (manufacturerValidate(manufacturer)){
            this.manufacturer = manufacturer;
        }
    }

    /**
     *
     * @return components
     */
    public Set getComponents() {
        return components;
    }


    /**
     *
     * @param components
     * @modifies: this.components
     * @effect: this object take components as attribute
     *
     */
    public void setComponents(Set components) {
        this.components = components;
    }


    /**
     *
     * @return object's description like PC:<"Vostro 3650MT","2016","Dell",Set:{"Intel-Core-i3-6100 CPU",
     *                                      "4GB-DDR3L RAM", "500GB-Sata hard disk", "Intel-HD graphics card"}>
     */
    public String toString(){
        return "PC:<" + this.model + " " + year + " " + this.manufacturer + " Set:{" + this.components.toString() + "}>";
    }

    /**
     *
     * @return object's description like PC:
     * 1 Vostro 3650MT 2016 DELL [Intel-Core-i3-6100 CPU,4GB-DDR3L RAM, ...]
     */
    public String toStringFormated(){
        return leftAlign(this.model.length(),this.model) + leftAlign(this.year.toString().length(), this.year.toString()) +
                leftAlign(this.manufacturer.length(), this.manufacturer) + leftAlign(this.components.toString().length(),
                this.components.toString());
    }
}
