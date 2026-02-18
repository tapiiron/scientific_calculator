# Usage Instructions

## How to run the program

mvn package<br>
java -jar target/calculator-1.0.0.jar<br>
<br>

## How to use the program

Program can be called with one argument to calculate the result of the expression<br>
<br>
Example usage:<br>
java -jar target/calculator-1.0.0.jar "2 + 3 * 4"<br>
<br><br>
If you want to use the program interactively, you can run the program without any arguments.<br>

## Example views from the program

<pre>
Shunting Yard Calculator
=========================
Variables set so far:

1. Enter calculation
2. Add/change variable
3. Add/change variable by calculation
4. Exit
Your wish? 
<b>1</b>
</pre>
<pre>
Remember to enter calculation in form of like '1 + 2 + MIN ( 1 , 2 )'
Enter calculation: 
<b>50 + ( 7 + 5 ) * 8</b>
Result: 146.0
</pre>

## Using variables in calculations

<pre>
Shunting Yard Calculator
=========================
Variables set so far:

1. Enter calculation
2. Add/change variable
3. Add/change variable by calculation
4. Exit
Your wish? 
<b>2</b>
Enter variable name (a-z): 
<b>g</b>
Enter variable value: 
<b>15</b>
</pre>
<pre>
Shunting Yard Calculator
=========================
Variables set so far:
g: 15.0

1. Enter calculation
2. Add/change variable
3. Add/change variable by calculation
4. Exit
Your wish? 
<b>1</b>
Remember to enter calculation in form of like '1 + 2 + MIN ( 1 , 2 )'
Enter calculation: 
<b>g + 10</b>
Result: 25.0
</pre>

## Setting variables by calculation

<pre>
Shunting Yard Calculator
=========================
Variables set so far:

1. Enter calculation
2. Add/change variable
3. Add/change variable by calculation
4. Exit
Your wish? 
<b>3</b>
Enter variable name (a-z): 
<b>a</b>
Remember to enter calculation in form of '1 + 2 + MIN ( 1 , 2 )'
Enter calculation: 
<b>MIN ( 5 , 2 ) + 9 * 4</b>
Result: 38.0

Shunting Yard Calculator
=========================
Variables set so far:
a: 38.0

1. Enter calculation
2. Add/change variable
3. Add/change variable by calculation
4. Exit
Your wish? 
</pre>

## Exiting the program

<pre>
Shunting Yard Calculator
=========================
Variables set so far:

1. Enter calculation
2. Add/change variable
3. Add/change variable by calculation
4. Exit
Your wish? 
<b>4</b>
</pre>