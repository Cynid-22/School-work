#include <iostream>

using namespace std;

int main()
{
    double price[12], profit=0, maxProfit=0;
    int dayBuy, daySell;


    cout << "Please type the 12-day price history of the stock: " << endl;
    for (int i = 0; i < 12; ++i) {
        cin >> price[i];
    }

    for (int i = 0; i < 9; ++i) {
        for (int j = i+3; j < 12; ++j) {
            profit = (1000/price[i])*price[j];
            if (profit > maxProfit) {
                maxProfit = profit;
                dayBuy = i;
                daySell = j;
            }
        }
    }
    if (maxProfit-1000 > 0) {
        cout << "Max profit: " << maxProfit-1000 << "." << endl;    
        cout << "Best strategy: Buy on day " << dayBuy+1 << ", sell of day " << daySell+1 << "." << endl;
    }
    else
        cout << "Max profit: 0." << endl;    


    return 0;
}

/*
Output:
Please type the 12-day price history of the stock: 
50 200 100 30 20 25 40 10 5 35 45 20
Max profit: 3500.
Best strategy: Buy on day 8, sell of day 11.
*/