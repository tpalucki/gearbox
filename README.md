# Gearbox Driver

This is the solution to a problem presented on the [DevUpgrade Online course](https://devupgrade.online/).

## Wymagania

1. Tryb dynamic - gdy jestesmy w uslizgu skrzynia nie powinna ingerowac w zmianę biegow.
2. Tryb ciągnięcia przyczepy - jeżeli jest podpieta przyczepa do haka z tyłu to należy hamować silnikiem. Nie hamuje nogą to skrzynia powinna tak ustawić bieg żęby obroty silnika hamowały. Ma działać tylko jeśli jedziemy w dół. NIe mamy takiej informacji ale można to wyciągnąć gdzieś ze śœiateł.
3. Mają się wyświetlać biegi, ma się dać wypływać na srzynie biegow. Skrzynia w trybie park. Jeśli hamulec wcisniety to można wrzucić tryb neutralny, można do tyłu mozna do przodu.
4. Gdy dodajemy gazu, wrzuca nam kolejne biegi - wrzystko oparte na obrotach.
5. Tryby działania: Eco, Comfort, Sport. Zmieniamy przyciskiem. Tryby zmieniaja moment w ktorym skrzynia wrzuca kolejny bieg. Jest tabelka, w ktorej jest napisane dla jakiego zakresu obrotów jest jaki bieg w danym trybie.
6. Kickdown - jesli wcisniemy gaz to redukuje. W trybie sport i sport plus redukuje o 2 biegi.
7. Manualna zmiana biegow lopatkami - zrzucamy recznie bieg nizej i na jakis czas skrzynia nie zmienia biegow - zaczyna to robic za jakis czas dopiero.
8. Tryb agresywnosci, 1 - gladko, bez szarpania itp, 2 - szybciej zmienia biegi (zmienia sie o 30% wg obrotow z tabelki), 3 - 30% zmiany + strzelanie z wydechu


-2 park
-1 - reverse
0 - neutral
1
2
3
4
5
6
7
8