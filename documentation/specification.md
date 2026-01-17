# Specification of scientific calculator
Degree programme: bachelor's in computer science (CS).

Programming language used in implementation: Java

This project implements scientific calculator using shunting-yard algorithm. With shunting yard algorithm input is transformed into Reverse Polish Notation (RPN). Program takes input of one calculation like 1+2*3 and uses shunting-yard to calculate the output for final calculation. Program takes as an input also functions sqrt,sin,min and max. When getting any kind of error, program returns no answer.

Program UI is a simple console based UI to receive input for calculation. Program returns calculated value. For invalid input program will return an error.

# Targeted time and space requirements
Shunting yard has linear time requirement O(n). As the input grows the time consumtion grows at about the same level.

# Sources used on implementation
- https://en.wikipedia.org/wiki/Shunting_yard_algorithm
- https://en.wikipedia.org/wiki/Reverse_Polish_notation

# For evaluations, other known programming languages
- C++
- PHP
- Perl
- Python (beginner)
 

# Original requirement specification (in finnish):
Toteuta laskin joka laskee annetun matemaattisen lausekkeen arvon, ja mahdollisesti sijoittaa sen muuttujaan, joita on käytettävissä riittävä määrä. Lauseke voi sisältää lukuarvoja, muuttujia, peruslaskutoimituksia ja sekä yhden (sqrt, sin) että kaksi parametria (min, max) saavia funktioita. Ohjelman tulisi antaa yksilöity virheilmoitus, jos käyttäjä syöttää virheellisen lausekkeen, ja erityisesti se ei saa ilmoittaa mitään arvoa lausekkeelle, jolle ei oikeasti voi laskea arvoa. Tälläinen ohjelma toteutetaan shunting-yard algoritmilla.
