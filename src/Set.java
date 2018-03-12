import java.util.ArrayList;
/**
 * @overview : Parts of (PC) is described in terms of the
 *             following attributes: parts
 * @attributes
 *  parts           String...
 *
 * @object
 *  A typical PC is <pre> v = <parts> </pre>, where
 *  <pre>parts</pre>
 * @abstract properties
 * mutable(parts) = True /\ optional(parts) = false
 *
 */
public class Set {
    private ArrayList<String> components = new ArrayList<>();

    /**
     *
     * @param parts
     *  add multiple parts into set's components if these are not duplicate
     */
    public Set(String... parts){
        for (String part : parts){
            if (!isDuplicate(part)){
                components.add(part);
            }
        }
    }

    /**
     *
     * @return components
     */
    public ArrayList getComponent(){
        return this.components;
    }

    /**
     *
     * @param parts
     * add parts in to arraylist or components
     */
    public void setComponents(ArrayList<String> parts){
        this.components = parts;
    }

    /**
     *
     * @param index
     * @effect this.components
     * @return true if remove successfully else of position index
     */
    public boolean removeComponent(int index){
        if (index>components.size()) return false;
        String name = components.get(index);
        if (this.components.remove(name)){
            System.out.println("Remove success: "+name);
            return true;
        }else{
            System.out.println("Can not remove that");
            return false;
        }
    }


    /**
     *
     * @param part
     * @return true if there is part exist in this.components
     */
    public boolean isDuplicate(String part){
        if (this.components.contains(part)){
            System.out.println("This part is already exist");
            System.out.println("Re-add please!");
            return true;
        }else
        return false;
    }


    /**
     *
     * @param part
     * @return true if part at last of this.components
     */
    private boolean isLast(String part){
        return this.components.get(components.size()-1).equals(part);
    }


    /**
     *
     * @param part
     * @effect this.components
     * @return true if this part is not duplicate and added successfully in this.components
     */
    public boolean addPart(String part){
        if (!isDuplicate(part)){
            this.components.add(part);
            System.out.println("Added "+ part + " into Set");
            return true;
        }else {
            System.out.println("Can not add " + part + "into this set");
            return false;
        }
    }


    /**
     *
     * @return description of this objects and add delemited point to classify each part
     */
    public String toString(){
        StringBuilder parts = new StringBuilder();
        parts.append("[");
        for (String part : components){
            if (!isLast(part)) {
                parts.append(" ").append(part).append(",");
            }else parts.append(" ").append(part);
        }
        parts.append("]");

        return parts.toString();
    }

}
