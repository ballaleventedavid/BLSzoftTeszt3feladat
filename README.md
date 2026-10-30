A számla induló egyenlege 0.

- deposit(double amount): hozzáadja a befizetett összeget az egyenleghez.
- withdraw(double amount): levonja a felvett összeget az egyenlegből
- getBalance(): visszaadja az egyenleget.

A projekt mappájából:

mvn clean test


Újrafuttatás: mvn test


- BankAccount: számlaegyenleg
- NotificationService: az értesítési szolgáltatás
- BankService: befizetés után meghívja az értesítést
- ExchangeRateService: az árfolyam lekérdezés
- `CurrencyConverter`: az összeget megszorozza az árfolyammal
- `BankServiceTest`: mock() példa
- `ExchangeRateServiceTest`: when(...).thenReturn(...) és a számítás ellenőrzése
- `BankAccountTest`: a számla működésének tesztje

## verziók

- Java 17
- JUnit Jupiter 5.11.3
- Mockito JUnit Jupiter 5.14.2
- Maven 3.9.9

