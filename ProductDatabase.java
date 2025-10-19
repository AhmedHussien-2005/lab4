import java.util.ArrayList;

public class ProductDatabase extends Data{
    public ProductDatabase(String filename){
        super(filename);
    }


    @Override
    protected Record createRecordFrom(String line){
        String[] parts = line.split(",");
        return new Product(parts[0],parts[1],parts[2],parts[3],Integer.parseInt(parts[4]),Float.parseFloat(parts[5]));
    }


    public ArrayList<Product> getAllProducts() {
    ArrayList<Product> products = new ArrayList<>();
    for (int i = 0; i < records.size(); i++) {
        Record record = records.get(i);
        if (record != null) {
            Product product = (Product) record;
            products.add(product);
        }
    }

    return products;
}
    
    public Product getProductRecord(String key) {
        Record record = getRecord(key);
        if (record!=null){
            return (Product) record;
        }
        else{
            return null;
        }
    }
}
