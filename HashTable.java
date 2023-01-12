public class HashTable<E> {

    private DLList<E>[] table;

    @SuppressWarnings("unchecked")
    public HashTable(){
       table = new DLList[21];
    }
  

    public void add(E obj){
        if(table[obj.hashCode()] == null){
            table[obj.hashCode()] = new DLList<E>();
        } 
        table[obj.hashCode()].add(obj);

        //make it sort when added
    }

    public void createList(int index){
        table[index] = new DLList<E>();
    }

    public void remove(E obj){
        table[obj.hashCode()].remove(obj);
    }

    public DLList<E> getList(int index){
        return table[index];
    }

    public boolean contains(E obj){
        if(table[obj.hashCode()].hasItem(obj)){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String returnString = "";
        for(int i = 0; i < 21; i++){
            if(table[i] != null){
                returnString += i + "$ - " + table[i].toString() + "\n";
            }
        }
        return returnString;
    }
    
}
