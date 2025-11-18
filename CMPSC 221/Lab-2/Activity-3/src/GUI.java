import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GUI implements ActionListener {
    Table table =  new Table();
    GridBagLayout gridBagLayout; GridBagConstraints gbc;
    JButton create, insertProduct, loadProduct, insertIngredients;

    String[] products= {""};

    String[] ingredients = {""};
    JComboBox<String> productList= new JComboBox<>(products);
    JList<String> listIngredient = new JList<>(ingredients);


    GUI(){
        createWindow();
    }

    public void createWindow(){
        gridBagLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        JFrame myFrame = new JFrame("Students Database");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(gridBagLayout);
        myFrame.setResizable(false);
        myFrame.setSize(600,450);
        addUI(myFrame);
        myFrame.setVisible(true);

    }

    public void addUI (JFrame frame){
        // Setting Components
        JLabel label1 = new JLabel("Product Names");
        JLabel label2 = new JLabel("List of Ingredients");

        create = new JButton("Create Tables");
        insertProduct = new JButton("Insert Products");
        insertIngredients = new JButton("Insert Ingredients");
        loadProduct = new JButton("Load Product's Name");

        // Adding ActionListener
        create.addActionListener(this);
        insertProduct.addActionListener(this);
        insertIngredients.addActionListener(this);
        loadProduct.addActionListener(this);

        productList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item
                String selectedItem = (String) productList.getSelectedItem();
                try {
                    // Get list of ingredients of product
                    ingredients = table.getIngredientsByProduct(selectedItem);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                // Update the JList
                listIngredient.setModel(new DefaultComboBoxModel<>(ingredients));

            }
        });



        // Setting gbc
        gbc.insets = new Insets(15,15,15,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding components
        // First row
        gbc.gridx=0; gbc.gridy=0; frame.add(label1,gbc);
        gbc.gridx=1; frame.add(productList,gbc);

        // Second row
        gbc.gridx=0; gbc.gridy=1; frame.add(label2,gbc);
        gbc.gridx=1; gbc.gridheight = 3; frame.add(listIngredient,gbc);


        // Third row
        gbc.gridx=0; gbc.gridy=4; gbc.gridheight = 1; frame.add(create,gbc);
        gbc.gridx=1; frame.add(insertProduct,gbc);

        // Fourth row
        gbc.gridx=0; gbc.gridy=5; frame.add(loadProduct,gbc);
        gbc.gridx=1; frame.add(insertIngredients,gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Create Tables":
                // Creates Table
                table.createTable();
                break;
            case "Insert Products":
                // Adds Products
                try {
                    table.insertProduct();
                } catch (SQLException ex) {
                    System.out.println("An error occurred while inserting into the database. Please try again.");
                }
                break;

            case "Load Product's Name":
                // Updates JComboBox

                try {
                    products = table.getProducts();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                productList.setModel(new DefaultComboBoxModel<>(products));
                break;
            case "Insert Ingredients":
                // Adds ingredients and also updates JList
                try {
                    table.insertIngredients();

                } catch (SQLException ex) {
                    System.out.println("An error occurred while inserting into the database. Please try again.");
                }
                break;
        }

    }
}
