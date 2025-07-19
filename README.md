# Projektdokumentation – Kochbuch-App

Dieses Dokument enthält die vollständige Dokumentation der **Kochbuch**-Applikation, erstellt im Rahmen der Module **M294 (Frontend)** und **M295 (Backend)**.

## Voraussetzungen

- **Java 21**
- **Maven 3.9.10**
- **Node.js**
- **npm** (wird mit Node.js installiert)
- **Git** (Github Account)
- **IDE** (am besten VSCode oder Codium)
- **MySQL Workbench**

Um Java, Maven, Node.js und Git zu installieren, empfiehlt es sich, den Chocolatey Package Manager zu nutzen.

Weitere Details findest du unter: https://chocolatey.org/

Nach der Installation über Chocolatey, kannst du mit folgenden Befehlen im Windows Terminal (cmd) prüfen, ob die Software und die korrekten Versionen davon installiert sind:

```bash
java --version
```

```bash
mvn -v
```

```bash
node -v
```

```bash
npm -v
```

```bash
git --version
```

---

## 1. Repository klonen

Öffne ein Terminal und führe folgenden Befehl aus:

```bash
git clone https://github.com/SilasHoeb25/m295_SE_CRUD.git
```

---


## Installationsanleitung

### 1. MySQL-Datenbank erstellen

Um das Projekt lokal starten zu können, wird eine MySQL-Datenbank benötigt. Gehe wie folgt vor:

- Erstelle eine neue lokale MySQL-Instanz (z. B. mit **XAMPP**, **MySQL Workbench** oder **Docker**).
- Die Zugangsdaten sollten wie folgt lauten:

  ```bash
  Benutzername: root
  Passwort: Test.123

 Öffne dein SQL-Tool oder ein Terminal und führe folgenden SQL-Befehl aus, um die Datenbank zu erstellen:

    CREATE DATABASE kochbuch;

### 2. Backend starten

Öffne ein Terminal (CMD)

Navigiere ins Backend-Verzeichnis:
  ```
  cd backend
  ```
Starte die Spring Boot Anwendung:
```
mvn spring-boot:run
```

Sollte die Verbindung zur Datenbank fehlschlagen:

    Öffne die Datei:

backend/src/main/resources/application.properties

Überprüfe, ob Benutzername, Passwort und Port korrekt sind:
        
        spring.datasource.url=jdbc:mysql://localhost:3306/kochbuch
        spring.datasource.username=root
        spring.datasource.password=Test.123
        

### 3. Frontend starten

Öffne ein neues Terminal-Fenster.

Navigiere ins Frontend-Verzeichnis:
```
cd frontend
```
Installiere die benötigten Abhängigkeiten:
```
npm install
```
Starte das Frontend im Entwicklungsmodus:
```
npm run dev
```
Danach kannst du die App im Browser aufrufen unter:

http://localhost:5173

Das Backend läuft unter: 
http://localhost:8080


---

## 1. Projektidee

### Übersicht
Mit dieser App kannst du Kochrezepte und deren Zutaten verwalten. Du kannst Rezepte erstellen, anzeigen, bearbeiten und löschen. Jedes Rezept kann mehrere Zutaten mit Mengenangabe enthalten, die beliebig angepasst oder entfernt werden können.

Die Anwendung soll die Verwaltung von Rezepten für Hobbyköche, Food-Blogger oder alle erleichtern, die ihre Lieblingsgerichte digital organisieren möchten.

### Ziel
Zutatenlisten und Kochanleitungen auf Papier oder in verstreuten Dateien zu verwalten, wird schnell chaotisch. Oftmals muss man Rezepte neu schreiben, vergisst Zutaten oder verliert Notizen.

Diese App löst dieses Problem, indem strukturierte Rezepte digital verwaltet werden können – klar, konsistent und im Browser. Ob beim täglichen Kochen oder bei der Wochenplanung: Kochbuch hilft dir, organisiert und inspiriert zu bleiben.

---

## 2. Anforderungen

### Funktionale Anforderungen

- Der Benutzer kann ein neues Rezept mit Titel, Beschreibung und Zutaten erstellen.
- Der Benutzer kann eine Liste aller Rezepte anzeigen.
- Der Benutzer kann Details eines Rezepts einsehen.
- Der Benutzer kann ein Rezept bearbeiten und Zutaten ändern.
- Der Benutzer kann ein Rezept löschen.
- Der Benutzer kann Zutaten abrufen und verwalten.

---

### Funktionsablauf (Frontend-Verhalten)

#### Rezeptübersicht
- Alle erstellten Rezepte werden in einer Kartenansicht angezeigt.
- Ein Button „+ Neues Rezept“ öffnet eine Seite zur Rezept-Erstellung.
- Ein Rezept kann per Klick auf die Karte angezeigt, bearbeitet oder gelöscht werden.

#### Rezept-Erstellung & Bearbeitung
- Ein Formular mit Eingabefeldern für:
  - Titel
  - Beschreibung
  - Dynamische Zutatenliste mit Name und Menge
- Buttons zum Speichern, Löschen und Zutaten hinzufügen.
- Validierung der Pflichtfelder vor dem Absenden.

#### Rezept-Details
- Zeigt Titel, Beschreibung und Zutatenliste eines Rezepts an.
- Optionen zum Bearbeiten oder Löschen.
- Sicherheitsabfrage vor Löschvorgängen.

---

### Nicht-Funktionale Anforderungen

- Alle Daten werden in einer MySQL-Datenbank gespeichert.
- Das Backend validiert Eingaben und stellt eine REST-API bereit.
- Das Frontend verarbeitet Fehler und prüft Eingaben.
- Die App lädt in unter 2 Sekunden und reagiert sofort auf CRUD-Aktionen.
- Es sind Unit-Tests für Backend und Frontend vorhanden.

---

## 3. Anwendungsfälle

### Anwendungsfall 1: Rezept erstellen
- **Akteur**: Benutzer  
- **Vorbedingung**: Benutzer befindet sich auf der Übersichtsseite  
- **Schritte**:  
  1. Klick auf „+ Neues Rezept“  
  2. Ausfüllen von Titel, Beschreibung und Zutaten  
  3. Klick auf „Speichern“  
- **Erwartetes Ergebnis**:  
  Das neue Rezept erscheint in der Übersicht.

---

### Anwendungsfall 2: Rezept bearbeiten
- **Akteur**: Benutzer  
- **Vorbedingung**: Mindestens ein Rezept vorhanden  
- **Schritte**:  
  1. Klick auf „Bearbeiten“ bei einem Rezept  
  2. Änderungen vornehmen  
  3. Klick auf „Speichern“  
- **Erwartetes Ergebnis**:  
  Änderungen werden gespeichert und angezeigt.

---

### Anwendungsfall 3: Rezeptdetails anzeigen
- **Akteur**: Benutzer  
- **Vorbedingung**: Mindestens ein Rezept vorhanden  
- **Schritte**:  
  1. Klick auf den Rezepttitel oder „Anzeigen“  
- **Erwartetes Ergebnis**:  
  Die Detailansicht des Rezepts wird geöffnet.

---

### Anwendungsfall 4: Rezept löschen
- **Akteur**: Benutzer  
- **Vorbedingung**: Mindestens ein Rezept vorhanden  
- **Schritte**:  
  1. Klick auf „Löschen“ bei einem Rezept  
  2. Löschung bestätigen  
- **Erwartetes Ergebnis**:  
  Das Rezept wird aus der Übersicht entfernt.

---

## 4. Klassendiagramm

Eine detailierte Übersicht mit Diagramm befindet sich im Ordner "Dokumentation"

### Entitäten & Beziehungen

- **Recipie**
  - `id` (String): Eindeutige ID
  - `name` (String): Rezeptname
  - `description` (String): Beschreibung

- **Ingredient**
  - `id` (String): Eindeutige ID
  - `name` (String): Zutatenname

- **RecipieIngredient**
  - `id` (String)
  - `recipieId` (String): Fremdschlüssel
  - `ingredientId` (String): Fremdschlüssel
  - `amount` (String): z. B. 2 TL, 100g

**Beziehungen:**  
Ein `Recipie` → hat viele `RecipieIngredient`  
Eine `Ingredient` → kann in vielen `RecipieIngredient` vorkommen

---

## 5. REST API

### Endpunkte

#### Rezept-Endpunkte

| Methode | Pfad                  | Beschreibung            |
|---------|-----------------------|--------------------------|
| GET     | `/api/recipies`       | Liste aller Rezepte      |
| GET     | `/api/recipies/{id}`  | Einzelnes Rezept anzeigen |
| POST    | `/api/recipies`       | Neues Rezept erstellen    |
| PUT     | `/api/recipies/{id}`  | Rezept aktualisieren      |
| DELETE  | `/api/recipies/{id}`  | Rezept löschen            |

#### Zutaten-Endpunkte

| Methode | Pfad                 | Beschreibung               |
|---------|----------------------|-----------------------------|
| GET     | `/api/ingredients`   | Liste aller Zutaten         |

### Beispiel-Daten (Payload)

```json
{
  "name": "Pasta Carbonara",
  "description": "Ein klassisches römisches Gericht.",
  "ingredients": [
    { "ingredientId": "abc123", "amount": "100g" }
  ]
}
