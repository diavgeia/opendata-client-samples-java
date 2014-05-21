Δι@ύγεια - Δείγματα κώδικα Java για κλήσεις προς το Opendata API
================================================================

Το project αυτό περιλαμβάνει δείγματα Java κώδικα στα οποία γίνεται επίδειξη
των κυριότερων κλήσεων που υποστηρίζονται από το Opendata API. Λεπτομέρειες
σχετικά με τις διαθέσιμες κλήσεις και τη μορφή των δεδομένων διατίθενται από τη
[σχετική σελίδα](https://test3.diavgeia.gov.gr/api/help) του διαδικτυακού τόπου της Δι@ύγειας.


*Σημείωση: Το project αυτό δεν συνιστά κάποιο "επίσημο" SDK, αλλά διατίθεται ως βοήθημα
στους developers οι οποίοι ενδιαφέρονται να αναπτύξουν δικές τους λύσεις για
τη διαλειτουργικότητα με το σύστημα της Δι@ύγειας. Ο κώδικας δεν είναι production-ready, επομένως η χρήση αυτούσιου του κώδικα ή αποσπασμάτων του θα πρέπει να αποφευχθεί.*

---



Περιεχόμενα project
-------------------

### Δείγματα κλήσεων

Τα δείγματα κλήσεων είναι ομαδοποιημένα σε Java packages ανάλογα με την σχετική
κατηγορία δεδομένων, και όλα βρίσκονται κάτω από το package **gr.gov.diavgeia.opendata.samples**:

- ```gr.gov.diavgeia.opendata.samples.decisions```: Υποβολή, ανάρτηση και ανάκτηση πράξεων
- ```gr.gov.diavgeia.opendata.samples.decisiontypes```: Πληροφορίες τύπων πράξεων
- ```gr.gov.diavgeia.opendata.samples.dictionaries```: Πληροφορίες λεξικών συστήματος
- ```gr.gov.diavgeia.opendata.samples.orgstructure```: Οργανόγραμμα 
- ```gr.gov.diavgeia.opendata.samples.orgstructure.org```: Πληροφορίες φορέων
- ```gr.gov.diavgeia.opendata.samples.orgstructure.unit```: Πληροφορίες οργανωτικών μονάδων φορέων 
- ```gr.gov.diavgeia.opendata.samples.orgstructure.signer```: Πληροφορίες υπογραφόντων φορέων
- ```gr.gov.diavgeia.opendata.samples.orgstructure.position```: Πληροφορίες οργανικών θέσεων
- ```gr.gov.diavgeia.opendata.samples.search```: Υποβολή αναζητήσεων (απλών και αναλυτικών)
- ```gr.gov.diavgeia.opendata.samples.search.terms```: Πληροφορίες διαθέσιμων όρων για τη διεξαγωγή αναλυτικών αναζητήσεων

### Βοηθητικός κώδικας

Για τη διεξαγωγή των κλήσεων και την επεξεργασία των αποτελεσμάτων είναι απαραίτητη η ύπαρξη μηχανισμών υποβολής HTTP requests, καθώς και μετατροπής των δεδομένων που αποστέλλονται και λαμβάνονται στο πλαίσιο των κλήσεων σε αντικείμενα Java. Ο απαιτούμενος κώδικας για αυτές τις εργασίες συγκεντρώνεται στα παρακάτω Java packages:

- ```gr.gov.diavgeia.opendata.http```: Δημιουργία και υποβολή HTTP GET/POST requests
- ```gr.gov.diavgeia.opendata.util```: Διάφορες συναρτήσεις για μετατροπές, δημιουργία και επεξεργασία δεδομένων
- ```gr.gov.diavgeia.opendata.json```: Κλάσεις που ακολουθούν τη δομή των JSON αιτημάτων και αποκρίσεων του API
- ```gr.gov.diavgeia.opendata.xml```: Κλάσεις που ακολουθούν τη δομή των XML αποκρίσεων του API

Σημειώνεται ότι οι κλάσεις του ```gr.gov.diavgeia.opendata.xml``` παράγονται αυτόματα με χρήση του [Maven JAXB 2 Plugin](https://java.net/projects/maven-jaxb2-plugin/pages/Home), το οποίο επεξεργάζεται το αρχείο XML schema που βρίσκεται στη διαδρομή ```src/main/xsd/diavgeia.xsd```.


---


Οδηγίες για τη χρήση και την εκτέλεση των παραδειγμάτων
-------------------------------------------------------

Το project μπορεί να αναγνωστεί από οποιοδήποτε Java IDE υποστηρίζει [Maven](http://maven.apache.org/) projects. Επιπλέον, το compilation και η εκτέλεση των παραδειγμάτων μπορούν να γίνουν και από κονσόλα, δεδομένου ότι το Maven βρίσκεται στο path του συστήματος.

**Κάποια χρήσιμα Maven goals**:

Παραγωγή JAXB-annotated κώδικα από το diavgeia.xsd: 

```
mvn generate-sources
```

Παραγωγή .jar: 

```
mvn package
```

Καθαρισμός παραγόμενων αρχείων:

```
mvn clean
```

### Εκτέλεση παραδείγματος κλήσης

Π.χ, αν επιθυμούμε να εκτελέσουμε το παράδειγμα που βρίσκεται στην κλάση ```gr.gov.diavgeia.opendata.samples.dictionaries.GetDictionariesJson```, εκτελούμε το εξής:

```
mvn exec:java -Dexec.mainClass=gr.gov.diavgeia.opendata.samples.dictionaries.GetDictionariesJson
```

#### Παράμετροι εκτέλεσης παραδειγμάτων

Υποστηρίζονται οι παρακάτω παράμετροι:

- ```opendata.root```: Το σταθερό τμήμα του URL κάθε υποστηριζόμενου REST resource του API. Default τιμή: ```https://test3.diavgeia.gov.gr/luminapi/opendata```
- ```opendata.auth```: Αν αυτή η παράμετρος είναι ```true```, τότε θα γίνει απόπειρα αυθεντικοποίησης κατά την κλήση.  Default τιμή: ```false```
- ```opendata.auth.username```: Το username του χρήστη που κάνει την κλήση. Υποχρεωτικό σε περίπτωση που το ```opendata.auth``` είναι ```true```. Δεν ορίζεται default τιμή. 
- ```opendata.auth.password```: Το password του χρήστη που κάνει την κλήση. Υποχρεωτικό σε περίπτωση που  το ```opendata.auth``` είναι ```true```. Δεν ορίζεται default τιμή. 

Παράδειγμα εκτέλεσης παραδείγματος με αυθεντικοποίηση:

```
mvn exec:java -Dexec.mainClass=gr.gov.diavgeia.opendata.samples.search.SimpleSearchJson -Dopendata.auth=true -Dopendata.auth.username=apiuser_1 -Dopendata.auth.password=ApiUser@1
```

---

Τρίτες βιβλιοθήκες και εργαλεία
-------------------------------

- [Apache Maven](http://maven.apache.org/): Source generation and builds
- [Apache HTTP Components](http://hc.apache.org/): Δημιουργία και υποβολή HTTP requests
- [Jackson 2](http://wiki.fasterxml.com/JacksonHome): JSON parsing 


