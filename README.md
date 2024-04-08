# Family_Hub

A projekt egy angular frontend és egy spring-boot backend alkalmazásból áll.

Backend:

Spring-boot 3.2.4-ben lett készítve 17-es java verzióban. A program képes az osztályok alapján hibernate segítségével elkészíteni az adatbázist.

Az alkalmazás a localhost:8080-as porton érhető el.

Az adatbázisban a következő táblák szerepelnek:

-> users - a felhasználók (id, név, email-cím, jelszó, szerepkör)

-> options - a felhasználók személyre szabásához szükséges adatok (háttérkép, profilkép, téma, user_id)

-> token - a felhasználók tokenei és állapota (ki van-e jelentkezve, token_id, token, user_id)

-> images - a feltöltött fényképek (kategória, image_id, kép neve, típus, kép, user_id)

-> apps - letölthető alkalmazások (műfaj, app_id, alkalmazás neve)

-> users_apps - az alkalmazásokat és usereket összekötő tábla (egy user-nek több alkalmazása is lehet és egy alkalmazást többen is letölthetnek)


Az alkalmazás az alábbiakra képes:

-> Fiókok regisztrálása, beléptetés, kijelentkezés, fióktörlés

-> Appok hozzáadása a menühöz, appok elindítása (kis ablakban megjelenik a neve és a műfaja)

-> A felhasználó adatainak megjelenítése a profil fülön

-> Háttérképek és profilképek feltöltése

-> Háttérképek és profilképek kilistázása

-> Háttérkép és profilkép beállítása (A users fölön az ikonok megjelenítése kicsit bugos)

-> Feltöltés teszt adatokkal

-> Az téma beállításához megcsináltam az endpointot de frontendben implementálni nem sikerült


Frontend:

A fronend Angular-ban lett implementálva.

Users menü:

Általában a localhost:4200-as portján indul el. 
Onnan autómatikusan a users fülre irányul, ahol kilistázódnak a felhasználók. Ha nincs egy se akkor lehet regisztrálni egy szülőt vagy a szimuláció gombra kattintva teszt felhasználókkal feltölteni. Ha nincs a felhasználó bejelentkezve minden felhasználó alatt ott a bejelentkezés gomb (ez nem az adott felhaszálóhoz kötött), ha pedig be van akkor vagy nincsen semmi a user kártyák láblécében vagy a törlés gomb jelenik meg. A szülők mindenkit ki tudnak törölni, a gyerekek csak saját magukat. Valamint az utolsó szülő csak akkor törölheti ki magát ha már nincs egyetlen gyerek sem a rendszerben.

Regisztráció:

Meg kell adni az adatokat (név, email, jelszó, szerepkör). A bejelentkezés gomb addig nem elérhető míg nem validak az adatok (minden ki van töltve, az email formátuma helyes valamint a név és jelszó megfelelnek a hosszkövetelményeknek). Regisztráció után visszavisz a felhasználók listázására. Regisztrációt csak a szülő felületéről lehet kezdeni amit a navigációs sávban érhet el.

Bejelentkezés:

Az email cím és jelszó megadásával történik, a validálás itt is érvényes. Ha sikertelen a bejelentkezés azt egy felugró ablak jelzi. Bejelentkezés után az alkalmazások menüre visz.

Alkalmazások:

Kilistázza az appokat valamint a nevükre kattintva meg is lehet őket nyitni. Az alkalmazás hozzáadása gombbal átnavigál a profil ablakra, ahol alkalmasásokat is lehet hozzáadni (a navigációs sávból is oda lehet jutni).

Profil:

Több szekcióból áll. Elsőnek kilistázza az adatokat mellette az aktuális profilképpel. Utána jönnak a profilképek és a háttérképek. Itt lehetőség van képet feltölteni, beállítani az aktuális profil/háttérképet valamint ezeket alaphelyzetbe állítani. Alattuk pedig az említett alkalmazások letöltése szekció, ahol gombok megnyomásával hozzá lehet adni az alkalmazást a menühöz.

Navigációs sáv:

Kijelentkezett állapotban csak a főoldal felirata látszik. Bejelentkezve Előjönnek a navigációs lehetőségek. Szülő esetében ez bűvül a felhasználó regisztrálásával. A kijelentkezésre kattintva kilép és visszavisz a felhasználók listájára.


Eddigi pályafutásom során Spring-boot-al nagyon keveset, Angularral egyáltalán nem találkoztam (csak vanilla js-el) szóval nem biztos hogy minden esetben a lehető legjobb megoldást választottam, valamint előfordulhatnak kisebb-nagyobb hibák.
