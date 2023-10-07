import java.util.ArrayList;
import java.util.HashMap;
public class MonthReport {
    FileReader fileReader = new FileReader();
    HashMap<Integer, MonthReport> checkReport = new HashMap<>();

    public ArrayList<Month> months = new ArrayList<>();

    public void loadFile(String filename) {
            ArrayList<String> lines = fileReader.readFileContents(filename);
            for (int j = 1; j < lines.size(); j++) {
                String[] parts = lines.get(j).split(",");
                String itemName = parts[0];
                Boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int unitPrice = Integer.parseInt(parts[3]);
                Month month = new Month(itemName, isExpense, quantity, unitPrice);
                months.add(month);

            }

        }



    public int getExpenses(){
        int sum = 0;
        for (Month month: months){
            if (month.isExpense) {
                sum += month.quantity * month.unitPrice;
            }
        }
        return sum;
    }

    public int getIncome(){
        int sum = 0;
        for (Month month : months){
         if (!month.isExpense){
             sum += month.quantity * month.unitPrice;
         }
        }
        return sum;
    }

    public Month getTopProduct() {
        Month maxItemName = null;
        for (Month month : months) {
            if (months.isEmpty()){
                System.out.println("Ошибка! Данные месяца не считаны.");
                continue;
            }
            if (!month.isExpense) {
                if (maxItemName == null) {
                    maxItemName = month;
                    continue;

                }
                if ((maxItemName.unitPrice * maxItemName.quantity) < (month.unitPrice * month.quantity)) {
                    maxItemName = month;
                }
            }
        }
        return maxItemName;
    }

    public Month getTopExpense(){
        Month maxExpense = null;
        for (Month month : months){
            if (month.isExpense){
                if(maxExpense == null){
                    maxExpense = month;
                    continue;
                }
                if ((maxExpense.unitPrice* maxExpense.quantity) < (month.unitPrice* month.quantity)){
                    maxExpense = month;
                }
            }
        }
        return maxExpense;
    }



}
