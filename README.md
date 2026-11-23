# Moj IT Servis — Rezervacija servisnega termina (Naloga 8)

Mobilna aplikacija **Moj IT servis** omogoča uporabnikom hitro in enostavno rezervacijo servisnega termina za računalniške naprave, mobilne telefone in tiskalnike. Aplikacija je razvita v programskem jeziku **Java** z uporabo klasičnega Android uporabniškega vmesnika v **XML** formatu.

---

## 📱 Funkcionalnosti aplikacije

1. **Začetni zaslon (`MainActivity`)**:
   - Izbira med tremi vrstami naprav: **Računalnik**, **Telefon** in **Tiskalnik**.
   - Sodoben vizualni izgled s karticami, opisom storitev in ikonicami.
   - Uporaba eksplicitnega `Intent` z dodanim podatkom (`putExtra`), ki posreduje izbrano napravo v drugo dejavnost.

2. **Obrazec za rezervacijo (`RezervacijaActivity`)**:
   - Pomični obrazec (`ScrollView`), prilagojen za različne velikosti zaslonov.
   - Vnos stikov in podatkov:
     - **Ime in priimek** (`inputType="textPersonName|textCapWords"`)
     - **E-poštni naslov** (`inputType="textEmailAddress"`)
     - **Telefonska številka** (`inputType="phone"`)
     - **Vrsta posega** (`Spinner` z možnostmi *Diagnostika*, *Popravilo*, *Nadgradnja*, *Drugo*)
     - **Opis težave** (`inputType="textCapSentences|textAutoCorrect|textMultiLine"`)
   - Izbira datuma in ure obiska prek pogovornih oken (`DatePickerDialog` in `TimePickerDialog`).
   - Onemogočanje izbire preteklih datumov.
   - Izbira načina kontakta prek `RadioGroup` (`E-pošta`, `Telefonski klic`, `SMS`) s prikazom `Toast` ob spremembi.
   - Preverjanje veljavnosti podatkov (validacija obveznih polj, format e-pošte, veljavnost datuma).

3. **Potrditev zahtevka (`AlertDialog`)**:
   - Prikaz povzetka rezervacije v `AlertDialog.Builder`.
   - Gumb **POTRDI** prikaže sporočilo o uspešni oddaji (`Toast`).
   - Gumb **PREKLIČI** zapre pogovorno okno in ohrani vnesene podatke.

4. **Dodatni izziv**:
   - Vnos zaščitne kode (`inputType="textPassword"`).
   - Gumb za hitri klic servisa z uporabo implicitnega `Intent` (`Intent.ACTION_DIAL`).

---

## 🛠️ Tehnične podrobnosti

- **Jezik**: Java (JDK 11)
- **Min SDK**: 29 (Android 10)
- **Target SDK**: 37
- **Uporabniški vmesnik**: Material Design 3, XML Layouts
- **Arhitektura**: Multi-Activity (`MainActivity`, `RezervacijaActivity`)

---

## 📦 Namestitvena datoteka (*.apk*)

Datoteka `.apk` za namestitev aplikacije na Android napravo se nahaja na poti:
```text
app/build/outputs/apk/debug/app-debug.apk
```

Za namestitev na telefon prenesite datoteko `app-debug.apk` in jo odprite na mobilni napravi (omogočite namestitev iz neznanih virov).

---

## 📸 Zaslonske slike aplikacije

| Začetni zaslon (`MainActivity`) | Obrazec (`RezervacijaActivity`) | Izbira datuma (`DatePicker`) | Potrditev (`AlertDialog`) |
| :---: | :---: | :---: | :---: |
| *(Priložite sliko 1)* | *(Priložite sliko 2)* | *(Priložite sliko 5)* | *(Priložite sliko 7)* |

---

## ❓ Odgovori na vprašanja iz učnega lista

### 1. V čem se pri `EditText` razlikujejo vrednosti `inputType="textEmailAddress"`, `"phone"` in `"textMultiLine"`?
- **`textEmailAddress`**: Prilagodi zaslonsko tipkovnico za vnos e-poštnih naslovov — na tipkovnici se prikažeta gumb `@` ter domenska pika `.`, ki olajšata vneseno e-pošto.
- **`phone`**: Prikaže številčnico z dodanimi znaki za telefonske številke (npr. `+`, `*`, `#`, premor), kar omogoča hiter vnos telefonske številke.
- **`textMultiLine`**: Omogoča večvrstični vnos besedila. Spremeni tipko za potrditev (Enter) v novo vrstico namesto prehoda na naslednje polje in samodejno razširi polje po višini.

### 2. Kakšna je naloga razredov `ArrayAdapter` in `Spinner` ter metode `onItemSelected()`?
- **`Spinner`**: Uporabniški gradnik (pustni izbirnik / padajoči meni), ki uporabniku omogoča izbiro ene vrednosti iz seznama ponujenih možnosti.
- **`ArrayAdapter`**: Vmesniški razred (adapter), ki povezuje podatkovni vir (npr. `string-array` iz `strings.xml` ali seznam v Javi) z gradnikom `Spinner` ter skrbi za pretvorbo elementov v ustrezne poglede (`View`).
- **`onItemSelected()`**: Klicna metoda (*callback*) vmesnika `OnItemSelectedListener`, ki se sproži, ko uporabnik klikne in izbere posamezno postavko v `Spinnerju`. Omogoča nam zajem izbrane vrednosti v kodi.

### 3. Zakaj uporabimo `RadioGroup`, če ponujamo tri medsebojno izključujoče možnosti?
`RadioGroup` je vsebnik za gradnike `RadioButton`, ki zagotavlja **medsebojno izključevanje** (*mutual exclusion*). Ko uporabnik izbere en `RadioButton` znotraj iste skupine `RadioGroup`, se vsi ostali gumbi v skupini samodejno odznačijo. Brez `RadioGroup` bi moral razvijalec ročno upravljati stanja vseh gumbov ob vsakem kliku.

### 4. Kakšna je razlika med `AlertDialog` in `DatePickerDialog` oziroma `TimePickerDialog`?
- **`AlertDialog`**: Splošno pogovorno okno za prikaz sporočil, opozoril ali povzetkov z možnostjo dodajanja naslova, besedila in gumbov za ukrepanje (npr. *POTRDI*, *PREKLIČI*).
- **`DatePickerDialog`**: Specializirano pogovorno okno, prilagojeno izbiri datuma (leto, mesec, dan) z integriranim koledarjem.
- **`TimePickerDialog`**: Specializirano pogovorno okno, prilagojeno izbiri ure in minut (v 12-urnem ali 24-urnem formatu) z urejevalnikom ur.

### 5. Kako s pomočjo `Intent` in `putExtra` prenesemo izbrano napravo v drugo dejavnost?
V začetni dejavnosti ustvarimo eksplicitni `Intent`:
```java
Intent intent = new Intent(MainActivity.this, RezervacijaActivity.class);
intent.putExtra("com.example.servis.EXTRA_DEVICE", deviceName);
startActivity(intent);
```
V ciljni dejavnosti (`RezervacijaActivity`) prevzamemo podatek v `onCreate()`:
```java
if (getIntent() != null && getIntent().hasExtra("com.example.servis.EXTRA_DEVICE")) {
    String deviceName = getIntent().getStringExtra("com.example.servis.EXTRA_DEVICE");
}
```

### 6. Kdaj se v vaši aplikaciji izvede potrditvena in kdaj preklicna veja dialoga?
- **Potrditvena veja (`setPositiveButton` — POTRDI)**: Izvede se, ko uporabnik v pogovornem oknu `AlertDialog` klikne na gumb "POTRDI". Ob tem se prikaže obvestilo `Toast` o uspešni demonstracijski oddaji rezervacije.
- **Preklicna veja (`setNegativeButton` — PREKLIČI)**: Izvede se, ko uporabnik klikne na gumb "PREKLIČI" ali zapre dialog s klikom izven okna. Dialog se zapre (`dismiss()`), uporabnik pa ostane na obrazcu z vsemi predhodno vnesenimi podatki.

---

## 📜 Licenca
Projekt je pripravljen kot šolska/študijska naloga pri predmetu Razvoj mobilnih aplikacij.
