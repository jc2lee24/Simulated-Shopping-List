public class Item {
    private String name;
    private int price;
    private int quantity;

    public Item(String name, int price, int quantity){
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return quantity;
    }

    public void changeQuantity(int num, boolean add){
        if(add){
            quantity += num;
        }
        if(!add){
            quantity -= num;
        }

    }



    @Override
    public int hashCode(){
        return this.price;
    }

    @Override
    public String toString(){
        return name + "(" + quantity + ") ";
    }

    @Override
    public boolean equals(Object o){
        Item compare = (Item)(o);
        if(compare.name.equals(this.name) && compare.price == this.price){
            return true;
        }
        return false;
    }


}
