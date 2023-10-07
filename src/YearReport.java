import java.util.ArrayList;

public class YearReport {

    FileReader fileReader = new FileReader();
    public ArrayList<Year> yearData = new ArrayList<>();


    public void loadFile(String path){
            ArrayList<String> lines = fileReader.readFileContents(path);
                for (int i = 1; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split(",");
                    int month = Integer.parseInt(parts[0]);
                    int amount = Integer.parseInt(parts[1]);
                    boolean isExpense = Boolean.parseBoolean(parts[2]);
                    Year year = new Year(month, amount, isExpense);
                    yearData.add(year);

                }
            }



    public int getYearPerMonthExpenses(int month){
        int expense = 0;
        for (Year year : yearData){
            if (year.month == month){
                if (year.isExpense){
                    expense = year.amount;
                }
            }
        }
        return expense;
    }


    public int getYearPerMonthIncome(int month){
        int income = 0;
        for (Year year : yearData){
            if(year.month == month){
                if(!year.isExpense){
                    income = year.amount;
                }
            }
        }
        return income;
    }


    public int getAverageYearExpense(){
        int sum = 0;
        int count = 0;
        for (Year year : yearData){
            if (year.isExpense){
                sum += year.amount;
                count ++;
            }
        }
        return sum/count;
    }


    public int getAverageYearIncome(){
        int sum = 0;
        int count = 0;
        for (Year year : yearData){
            if(!year.isExpense){
                sum += year.amount;
                count ++;
            }
        }
        return sum/count;
    }




}
