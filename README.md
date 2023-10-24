# Amőba
## 1	A program célja

A feladat egy olyan program készítése, melyben amőbát lehet játszani. A játékben két játékos játszik egymás ellen. A játék elején be kell állítani, hogy mekkora méretű legyen a játéktér (3-10) és hány szomszédos mező kell a győzelemhez (3-5). Ezt követően a játék elindul és a játékosok egymást követően rakhatják le a saját karakterüket („X” vagy „O”) a még szabad cellákra.

A játék kétféleképpen érhet véget:

- Nem marad több szabad mező. Ekkor a játék döntetlen
- Az egyik játékosnak összejön egymás mellett a győzelemhez szükséges mező. Ez lehet egy sorban, egy oszlopban, vagy valamelyik átlóban. Ekkor ez a játékos nyer.

## 2	  A program futtatása

### 2.1	Főmenü

A játék indítása után egy grafikus főmenü jelenik meg, melyben 3 lehetőség közül választhat a felhasználó, melyek:

- Új játék indítása
- Mentett játék betöltése
- Kilépés

### 2.2	Új játék indítása

Az új játék indításakor beállítható lesz a két játékos neve, a játéktér mérete és a győzelemhez szükséges cellák. Ezt követően elindítható maga a játék. A játék folyamán a játékosok az üres cellákra tudják lehelyezni saját karakterüket. Amennyiben olyan mezőre kattintanak, mely már nem üres, akkor nem történik semmi.

A játék végén a mező fent marad és megjelenik a képernyőn a győztes játékos neve. Ekkor már nem lesz lehetőség menteni, azonban lesz lehetőség újrakezdeni a játékot azonos nevekkel, felcserélt karakterekkel.

A játék közbeni képernyőn mindig látszik a két játékos neve, karaktere, valamint az, hogy ki következik. Ezen felül egy befejezés és egy mentés gomb. Előbbi a játékot befejezi, annak újrakezdésére ezt követően nincs lehetőség. A mentés gombnál megadható egy név, hogy milyen néven mentse az aktuális állást, mely a Mentett játék betöltésével újra folytatható.

### 2.3	Mentett játék betöltése

A Mentett játék betöltése gombra való kattintás után lehetőség lesz megadni egy nevet, amilyen néven a játszma mentve lett. Ezt követően a játék onnét folytatódik, ahol azt mentették.

## 3	Kivitelezés

A játék grafikus környezetben fog futni, melyet swing segítségével valósítok meg. A játékok mentése a fájl mellett létrehozott saves mappába fog kerülni. A mentések neve a megadott név lesz, valamint .dat kiterjesztést fog kapni. A mentett állások JSON-be lesznek mentve.

## 4	Továbbfejlesztési lehetőségek

Az alkalmazás tervezett verzióját több módon is tovább lehet fejleszteni. Lehetőség van arra, hogy a játéktér a 10x10-esnél nagyobb lehessen, akár úgy, hogy görgetni lehessen azt jobbra-balra és fel-le. Emellett lehetőség van arra is, hogy a játékosok ne csupán „X”-el és „O”-val legyenek, hanem saját színük lehessen, így jobban kiemelve saját mezőiket.
