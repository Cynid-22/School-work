    #include <iostream>

using namespace std;

int main()
{
    char SodaName;
    double InputMoney, Change;
    cout << "Enter the soda name (Sprite=s, Fanta=f, CocaCola=c, Dr.Pepper=d): ";
    cin >> SodaName;
    cout << "Input the money: $";
    cin >> InputMoney;
    switch (SodaName)
    {
    case 's':
        cout << "You order Sprite";
        Change = InputMoney - 2.68;
        break;
    case 'f':
        cout << "You order Fanta";
        Change = InputMoney - 1.78;
        break;
    case 'c':
        cout << "You order CocaCola";
        Change = InputMoney - 2.68;
        break;
    case 'd':
        cout << "You order Dr.Pepper";
        Change = InputMoney - 1.98;
        break;
    default:
        cout << "The soda you order is out of sale.";
        break;
    }
    cout << "\nYour excharge: $" << Change;
    return 0;
}

/*
output:
Enter the soda name (Sprite=s, Fanta=f, CocaCola=c, Dr.Pepper=d): s
Input the money: $10
You order Sprite
Your excharge: $7.32
*/