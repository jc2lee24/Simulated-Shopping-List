import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JPanel implements ActionListener{
    
    HashTable<Item> items;

    private String drawString = "";

    private JButton addButton;
    private JButton removeButton;
    private JButton searchButton;
    private JTextField addName;
    private JTextField addPrice;
    private JTextField addQuantity;
    private JTextArea listDisplay;
    private JTextArea searchDisplay;

    public Screen(){
        this.setLayout(null);
        items = new HashTable<Item>();
        items.add(new Item("Apple", 1, 20));
        items.add(new Item("Berry", 1, 100));
        items.add(new Item("Cereal", 3, 99));
        items.add(new Item("Mango", 3, 5));
        items.add(new Item("Milk", 3, 10));
        items.add(new Item("Turkey", 15, 15));

        addButton = new JButton();
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setHorizontalAlignment(SwingConstants.CENTER);
        addButton.setBounds(80, 555, 200, 30);
        addButton.setText("ADD ITEM");
        this.add(addButton);
        addButton.addActionListener(this);

        removeButton = new JButton();
        removeButton.setFont(new Font("Arial", Font.BOLD, 20));
        removeButton.setHorizontalAlignment(SwingConstants.CENTER);
        removeButton.setBounds(305, 555, 200, 30);
        removeButton.setText("REMOVE ITEM");
        this.add(removeButton);
        removeButton.addActionListener(this);

        searchButton = new JButton();
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchButton.setBounds(530, 555, 200, 30);
        searchButton.setText("SEARCH ITEM");
        this.add(searchButton);
        searchButton.addActionListener(this);

        addName = new JTextField();
        addName.setFont(new Font("Arial", Font.PLAIN, 20));
        addName.setHorizontalAlignment(SwingConstants.CENTER);
        addName.setBounds(80, 505, 200, 30);
        addName.setText("name");
        this.add(addName);

        addPrice = new JTextField();
        addPrice.setFont(new Font("Arial", Font.PLAIN, 20));
        addPrice.setHorizontalAlignment(SwingConstants.CENTER);
        addPrice.setBounds(305, 505, 200, 30);
        addPrice.setText("price");
        this.add(addPrice);

        addQuantity = new JTextField();
        addQuantity.setFont(new Font("Arial", Font.PLAIN, 20));
        addQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        addQuantity.setBounds(530, 505, 200, 30);
        addQuantity.setText("quantity");
        this.add(addQuantity);

        listDisplay = new JTextArea();
        listDisplay.setFont(new Font("Arial", Font.PLAIN, 10));
        listDisplay.setBounds(50,50,700,400);
        listDisplay.setText(items.toString());
        listDisplay.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(listDisplay);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(50,50,700,350);
        this.add(scrollPane);

        searchDisplay = new JTextArea();
        searchDisplay.setFont(new Font("Arial", Font.PLAIN, 10));
        searchDisplay.setBounds(50,410,700,50);
        searchDisplay.setEditable(false);
        
        JScrollPane scrollPane2 = new JScrollPane(searchDisplay);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setBounds(50,410,700,50);
        this.add(scrollPane2);

    }

    public Dimension getPreferredSize(){
        return new Dimension(800, 600);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawString(drawString, 500, 100);

    }

    public void actionPerformed(ActionEvent e){
        String name = addName.getText();
        int price = Integer.parseInt(addPrice.getText());
        int quantity = Integer.parseInt(addQuantity.getText());

        if(e.getSource() == addButton){
            add(name, price, quantity);
        }

        else if(e.getSource() == removeButton){
            remove(name, price, quantity);
        }

        else if(e.getSource() == searchButton){
            drawString = getItem(name, price);
        }

        listDisplay.setText(items.toString());
        searchDisplay.setText(drawString);
        repaint();
    }

    public void add(String name, int price, int quantity){
        Item tempItem = new Item(name, price, quantity);
        DLList<Item> tempList = items.getList(tempItem.hashCode());
        
        if(tempList != null){//is there a list?
            if(tempList.hasItem(tempItem)){//is it already in the list?
                tempList.get(tempItem).changeQuantity(quantity, true);
            }
            else{//add alphabetically
                for(int i = 0; i < tempList.size(); i++){
                    if(tempList.get(i).getName().compareTo(name) > 0){
                        tempList.add(tempItem, i);
                        break;
                    }
                }
            }
        }
        else{//add regularly
            items.add(tempItem);
        }
    }

    public void remove(String name, int price, int quantity){
        Item tempItem = new Item(name, price, quantity);
        DLList<Item> tempList = items.getList(tempItem.hashCode());

        if(tempList.get(tempItem).getQuantity() <= quantity){
            tempList.remove(tempItem);
        }
        else{
            tempList.get(tempItem).changeQuantity(tempItem.getQuantity(), false);
        }

    }

    public String getItem(String name, int price){
        DLList<Item> tempList = items.getList(price);
        String returnString = tempList.get(new Item(name, price, 0)).toString();
        return returnString;
    }
}
