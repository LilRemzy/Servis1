# Moj IT servis (MojITServis)

V tej nalogi sem naredil demonstracijsko aplikacijo **Moj IT servis** za naročanje računalniških naprav na servis. Aplikacija ima dve aktivnosti: `MainActivity` (izbira naprave) in `RezervacijaActivity` (obrazec za rezervacijo termina). Uporabnik izbere napravo, vnese kontaktne podatke, opiše težavo, določi datum in uro, izbere način kontakta in potrdi zahtevek. Aplikacija ne potrebuje strežnika, zahtevek se v resnici ne pošlje.

## Cilji

- izdelati novo aplikacijo z imenom `MojITServis` s predlogo Empty Views Activity in programskim jezikom Java,
- v `MainActivity` pripraviti začetni zaslon s tremi klikljivimi slikami (`ImageView`): Računalnik, Telefon in Tiskalnik, vsaka s kratkim opisom in `contentDescription`,
- ob kliku z eksplicitnim `Intent` zagnati `RezervacijaActivity`, izbrano napravo posredovati s `putExtra` in jo izpisati v naslovu obrazca,
- v `activity_rezervacija.xml` pripraviti pomični obrazec (`ScrollView`) z naslovi razdelkov, poljema `EditText` za ime in priimek (`textPersonName`) in e-pošto (`textEmailAddress`), poljem za telefon (`phone`), poljem za opis težave (`textCapSentences | textAutoCorrect | textMultiLine`) ter ustreznimi namigi (`hint`),
- dodati izbirnik `Spinner` z vrstami posega (Diagnostika, Popravilo, Nadgradnja, Drugo), vrednosti shraniti v `string-array` v `strings.xml`, izbiro pa zajeti z `ArrayAdapter` in `OnItemSelectedListener`,
- dodati izbiro datuma (`DatePickerDialog`) in ure (`TimePickerDialog`) s `DialogFragment` in ustreznima poslušalcema, izbrani vrednosti pa izpisati v obrazcu,
- dodati `RadioGroup` s tremi možnostmi (E-pošta, Telefonski klic, SMS), pri čemer se ob spremembi izbira shrani in prikaže v `Toast`,
- gumb **POTRDI REZERVACIJO** naj preveri obvezna polja, datum, uro in način kontakta (datum ne sme biti v preteklosti), pri manjkajočih podatkih pa prikaže opozorilo (`setError` ali `Toast`) in ne odpre potrditvenega okna,
- z `AlertDialog.Builder` prikazati povzetek rezervacije z gumboma **POTRDI** in **PREKLIČI**,
- vsa uporabniku vidna besedila zapisati v `strings.xml`,
- spremeniti privzeto ikono aplikacije z orodjem Image Asset Studio (Launcher Icons),
- generirati datoteko `.apk` in aplikacijo namestiti na telefon z Androidom.

## Delovanje aplikacije

| Element | Kaj naredi | Uporabljeno |
|---------|-----------|-------------|
| **Slike naprav** | odprejo obrazec za izbrano napravo, ki se izpiše v naslovu | `Intent` (eksplicitni) + `putExtra` |
| **Ime, e-pošta, telefon, opis** | vnos podatkov z ustrezno zaslonsko tipkovnico | `EditText` + `inputType` |
| **Vrsta posega** | izbira ene od štirih možnosti | `Spinner` + `ArrayAdapter` + `OnItemSelectedListener` |
| **Datum** | odpre koledar, izbrani datum se izpiše v obrazcu | `DatePickerDialog` + `DialogFragment` |
| **Ura** | odpre izbirnik ure, izbrana ura se izpiše v obrazcu | `TimePickerDialog` + `DialogFragment` |
| **Način kontakta** | izbrana je lahko le ena možnost, izbira se prikaže v obvestilu | `RadioGroup` + `Toast` |
| **POTRDI REZERVACIJO** | preveri vnos in prikaže povzetek rezervacije | `AlertDialog.Builder` |

### Preverjanje vnosa

- ime in priimek, e-pošta, telefon in opis težave so obvezni (opozorilo z `setError`),
- e-pošta mora biti v veljavni obliki,
- obvezni so tudi datum, ura in način kontakta (opozorilo s `Toast`),
- datum ne sme biti v preteklosti,
- če kateri podatek manjka, se potrditveno okno ne odpre.

### Potrditveno okno

Povzetek vsebuje napravo, vrsto posega, ime, datum, uro in način kontakta. Gumb **POTRDI** prikaže `Toast` o uspešni demonstracijski oddaji, gumb **PREKLIČI** pa okno zapre in uporabnik ostane v obrazcu.

## Zaslonske slike aplikacije
<img width="200" height="411" alt="Slika1" src="https://github.com/user-attachments/assets/038274b9-db20-4d04-a16d-bf44a4e704eb" />
<img width="200" height="411" alt="Slika2" src="https://github.com/user-attachments/assets/4b6e98ce-3a91-4c9a-92cf-2cd6dc24b4b1" />
<img width="200" height="411" alt="Slika3" src="https://github.com/user-attachments/assets/385d82ba-fd61-4163-be8e-aed5dd4c8b4a" />
<img width="200" height="411" alt="Slika4" src="https://github.com/user-attachments/assets/baa63286-5546-4bd2-bb98-2ff5d97c1059" />



## Tehnologije

- Java, AndroidX (`AppCompatActivity`, `DialogFragment`)
- XML postavitve (`ScrollView`, `LinearLayout`)
- `Spinner`, `RadioGroup`, `DatePickerDialog`, `TimePickerDialog`, `AlertDialog`
- Image Asset Studio za ikono aplikacije

