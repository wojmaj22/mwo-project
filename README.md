# CRUD application
Projekt zawiera prostą aplikację MVC wykonaną w sping mvc impelentującą operacje CRUD. Opis działania programu, testów
oraz raportowania bugów do Azure DevOps znajduje się poniżej. Oprócz tego w pluku mp4 pokazane działanie w praktyce.
## Uruchomienie
Do uruchomienia nie potrzeba żadnej konfiguracji, 
natomiast należy mieć poprawnie zainstalowane java jdk 17 oraz maven.
Aby uruchomić aplikację należy wykonać:
```bash
mvn spring-boot:run
```
Aby uruchomić testy aplikacji należy wykonać:
```bash
mvn test
```
Budowanie aplikacji:
```bash
mvn clean package
```
## Funkcjonalności
Po uruchomieniu aplikacji dostępna jest ona pod adresem `localhost:8080/list`
gdzie wyświetlana jest podstawowa lista elementów z opcjami edycji, usuwania i dodawania elementów.

## Działanie testów Selenium
Na zdjęciu przykład testów oraz poprawnego działania przez `mvn test`  
![seleniumtests.png](pictures%2Fseleniumtests.png)  
## Konfiguracja Azure DevOps
Stworzenie projektu:  
![emptyboard.png](pictures%2Femptyboard.png)  
Wygenerowanie PAT:  
![pat.png](pictures%2Fpat.png)  
## Konfiguracja GitHub Actions
Stworzenie GitHub Actions:  
![pipeline.png](pictures%2Fpipeline.png)  
Stworzenie GitHub Token  
![githubtoken1.png](pictures%2Fgithubtoken1.png)  
Dodanie sekretów do GitHub Secrets:  
![githubtoken.png](pictures%2Fgithubtoken.png)  
## Integracja z Azure DevOps
Integracja jest automatyczna przy podanej powyżej konfiguracji Actions oraz wygenerowaniu tokenów  
## Test działania GitHub Actions:
Najpierw testujemy pull request działającego kodu:  
![goodpullrequest.png](pictures%2Fgoodpullrequest.png)  
Jak widać testy przeszły pomyślnie, teraz dodajemy test który będzie błędny:
![failedtest.png](pictures%2Ffailedtest.png)  
Po dodaniu do brancha i stworzeniu pull request, mamy błąd przy sprawdzeniu:  
![failedpullrequest.png](pictures%2Ffailedpullrequest.png)  
Automatycznie tworzy się zgłoszenie Buga na tablicy Azure DevOps:
![createdbug.png](pictures%2Fcreatedbug.png)