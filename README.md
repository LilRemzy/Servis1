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

## 📸 Zaslonske slike aplikacije

| Začetni zaslon (`MainActivity`) | Obrazec (`RezervacijaActivity`) | Izbira datuma (`DatePicker`) | Potrditev (`AlertDialog`) |
| :---: | :---: | :---: | :---: |
| *(Priložite sliko 1)* | *(Priložite sliko 2)* | *(Priložite sliko 5)* | *(Priložite sliko 7)* |

---

## 📜 Licenca
Projekt je pripravljen kot šolska/študijska naloga pri predmetu Razvoj mobilnih aplikacij.
