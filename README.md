/* TEMA1 PROIECTAREA ALGORITMILOR ANCA-MARIA COLACEL 324CC /*

--- ORGANIZARE ---

1. Problema 1- Feribot
Am rezolvat aceasta problema utilizand tehnica Divide & Impera. La baza rezolvarii se afla cautarea binara. Am implementat o metoda numita bnSearch care calculeaza in mod clasic mijlocul din vector. Vectorul contine numere cuprinse intre maximul din vectorul de masini si suma tututror greutatilor masinilor. Atata vreme cat marginea din stanga nu este egala cu cea din dreapta, adica nu s-a ajuns la finalul vectorului, aplic functia verifica. Aceasta functie parcurge fiecare masina in parte si daca greutatea masinii adaugata la costul curent este mai mica sau egala decat costul dat ca parametru adica mijlocul intervalului, se adauga acea masina la cost. In caz contrar, se incrementeaza numarul de feriboturi si costul noului feribot este initializat cu masina a carei greutate aducea depasire. In final verific sa nu folosesc mai multe feriboturi decat exista.
Daca functia verifica intoarce true pot gasi un numar mai mic asa ca mut in partea stanga a intervalului, in caz contrar caut in dreapta. Acest lucru are sens intrucat vectorul are numere in ordine crescatoare.
Complexitatea acestei probleme este O(nlogm). O(log m) pentru ca se aplica cautare binara pe acel interval (sa zicem ca are m dimensiunea) si O(n) din functia de verificare care parcurge vectorul de
n masini.

2. Problema 2- Nostory

Cerinta 1. Am rezolvat aceasta cerinta folosind tehnica Greedy. Primul vector este sortat crescator, al doilea este sortat descrescator. Parcurg cei doi vectori si compar elementele de pe aceleasi pozitii pentru a descoperi maximul pe care mai apoi il adaug la suma.
Complexitatea acestei probleme este O(nlogn). O(logn) de la sortarilor vectorilor si O(n) de la parcurgerile lor.

Cerinta 2. Am rezolvat aceasta cerinta folosind de asemenea Greedy. Am format intai de toate suma folosind elementele maxime de pe fiecare pozitie. Am format 2 vectori, unul cu elementele maxime de pe fiecare pozitie din a si b si unul cu elementele minime din a si b.
Am sortat vectorul maxim crescator si vectorul minim descrescator. Tinand cont de numarul de mutari pe care le pot face compar elementele de pe aceeasi pozitie din vectorii de minim si de maxim si daca in vectorul de minim gasesc ceva mai mare decat pe pozitia din vectorul de maxim scot elementul din suma si il pun pe cel mai mare gasit. De asemenea, dupa ce fac aceasta schimbare decrementez numarul de mutari.
Complexitatea acestei probleme este O(nlogn). O(logn) de la sortarilor vectorilor si O(n) de la parcurgerile lor.

3. Problema 3- Sushi

Cerinta 1: Am rezolvat aceasta cerinta folosind programare dinamica, mai exact am urmat exemplul de implementare si relatia de recurenta descrise in laboratorul 3, Programare Dinamica, tipul Rucsacului.
Modificarile aduse tin de concepte, anume ca aici obiectele erau tipurile de sushi, banii erau greutatea si profitul erau notele. Am folosit o matrice 2D, am luat cazul de baza dp[0][j] = 0 pentru ca daca nu exista niciun tip de sushi nu ai ce cumpara, apoi am pornit cu 2 for-uri pentru a parcurge tipurile de sushi si banii avuti la dispozitie. Putem alege sa nu cumparam acel tip de sushi daca nu ne sporeste maximul de note sau putem alege sa il cumparam daca ne aduce mai mult profit. Astfel, scris ambele metode si am luat maximul dintre ele. Am calculat de asemenea cu un for si suma notelor pentru fiecare coloana din matrice adica pentru fiecare tip de sushi, care ma ajuta la suma notelor generale.
Complexitatea acestei probleme este O(m*n*x) datorita celor 2 for-uri imbricate, primul care parcurge tipurile de platouri si rezulta O(m) si al doilea care parcurge banii si rezulta O(n*x).

Cerinta 2: Am rezolvat aceasta problema plecand de la acelasi algoritm pe care l-am imbunatatti foarte putin. PLec de la acelasi caz de baza apoi pornesc cu cele 2 for-uri, implementez sa nu cumpar acel tip de sushi, implementez sa cumpar acel tip de sushi o singura data si dupa verific daca am bani sa-l cumpar si daca ma ajuta sa fac asta, apoi implementez sa-l cumpar si pentru a doua oara, verific daca am bani sa-l cumpar si daca asta imi sporeste profitul sau nu. La final intorc notele maxime obtinute.
Complexitatea acestei probleme este de asemenea este de asemenea O(m*n*x), iar explicatia este de acele 2 for-uri identice.

Cerinta 3: Am rezolvat aceasta problema plecand de la cerinta 2. Imbunatatirea vine prin folosirea unei matrice 3D unde a treia dimensiune este data de numarul de platouri cumparate care sa nu depaseasca n, numarul de prieteni. Dupa care am pornit cu acelasi caz de baza, am implementat sa nu cumpar acel platou, apoi sa-l cumpar o singura data si am verificat daca am bani pentru a-l cumpara si daca k (variabila care parcurge numarul de platouri) este mai mare decat 0. Pentru a doua situatie implementez cumpararea a doua platouri, verific daca am bani pentru ele si daca k<1 pentru ca un platou de acelasi tip deja e cumparat si desigur in fiecare situatie cumpar acel platou daca chiar ma ajuta, prin calcularea maximului.
Complexitatea acestei probleme este O(m*n^2*x) pentru ca pe langa cele 2 for-uri folosite la primele cerinte apare acel for in plus care parcurge pana la n platouri egale cu n prieteni.

4. Problema 4- Semnale

Cerinta 1. Am rezolvat aceasta cerinta folosind programarea dinamica. Folosesc o matrice 3D care retine numarul de biti de 0, numarul de biti de 1 si daca semnalul se termina cu 0 sau 1. Initializez cazurile de baza, un singur semnal cu 0 biti de 0, 0 biti de 1 si terminat in 0, am considerat acest semnal vid. Apoi indiferent de numarul de biti de 0, un semnal fara biti de 1 si terminat in 1 este 0, la fel si unul fara biti de 0 terminat in 0. Parcurg numarul de biti de 0 si de 1 si daca i si j sunt pozitive ma bazez pe faptul ca un semnal terminat in 0 poate aparea prin a adauga un bit de 0 atat la un semnal terminat in 0 cat si terminat in 0. Un semnal terminat in 1 se formeaza doar prin adaugarea unui bit de 1 la un semnal terminat in 0 pentru ca nu putem avea 2 biti de 1 consecutivi.
In final, numarul total de semnale este suma numarului total de semnale terminate in 0 si a celor terminate in 1, folosind x biti de 0 si y biti de 1.
Complexitatea acestei probleme este O(xy) datorita celor 2 parcurgeri cu for-uri imbricate.

--- Bibliografie ---
- In rezolvarea acestei teme m-au ajutat foarte mult cursurile si laboratoarele aferente acestei materii de la capitolele Divide & Impera, Greedy si Programare dinamica.
- https://www.geeksforgeeks.org/dynamic-programming/
- https://www.programiz.com/dsa/dynamic-programming
