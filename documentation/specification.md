# Specification of scientific calculator
Degree programme: bachelor's in computer science (CS).

Programming language used in implementation: Java

This project implements scientific calculator using shunting-yard algorithm. With shunting yard algorithm input is transformed into Reverse Polish Notation (RPN). 

Program UI is a simple console based UI to receive input for calculation. Program returns calculated value. For invalid input program will return an error.

TODO:
Tavoitteena olevat aika- ja tilavaativuudet (esim. O-analyysit)
Tästä kannattaa selvittää niin paljon kuin voitte. Ei ole tarkoitus todistaa tai mitata mitään itse.
Käytä aika ja tilavaatimuuksia apuvälineenä ymmärtääksenne, miten työhön kannattaa asennoitua.
Nämä kannattaa katsoa wikipediasta ja varmistaa, että ymmärrätte oman algoritmin kohdalla mistä ne tulevat. Miksi algoritmisi tarvitsee sen verran aikaa?

Sources used on implementation:
- https://en.wikipedia.org/wiki/Shunting_yard_algorithm
- https://en.wikipedia.org/wiki/Reverse_Polish_notation

For evaluations other known programming languages:
- C++
- PHP
- Perl
- Python
 

Original requirement specification (in finnish):
Toteuta laskin joka laskee annetun matemaattisen lausekkeen arvon, ja mahdollisesti sijoittaa sen muuttujaan, joita on käytettävissä riittävä määrä. Lauseke voi sisältää lukuarvoja, muuttujia, peruslaskutoimituksia ja sekä yhden (sqrt, sin) että kaksi parametria (min, max) saavia funktioita. Ohjelman tulisi antaa yksilöity virheilmoitus, jos käyttäjä syöttää virheellisen lausekkeen, ja erityisesti se ei saa ilmoittaa mitään arvoa lausekkeelle, jolle ei oikeasti voi laskea arvoa. Tälläinen ohjelma toteutetaan shunting-yard algoritmilla.
