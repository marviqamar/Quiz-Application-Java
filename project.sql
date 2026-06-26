CREATE DATABASE quiz_application;
USE quiz_application;

CREATE TABLE questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(500) NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_option CHAR(1) NOT NULL,
    difficulty VARCHAR(20) DEFAULT 'easy'
);

INSERT INTO questions 
(question_text, option_a, option_b, option_c, option_d, correct_option)
VALUES
('Which language is platform independent?', 'C', 'C++', 'Java', 'Python', 'C'),
('Which keyword is used to inherit a class in Java?', 'this', 'super', 'extends', 'implements', 'C'),
('Which company developed Java?', 'Microsoft', 'Apple', 'Sun Microsystems', 'Google', 'C'),
('Which method is the entry point of Java program?', 'start()', 'main()', 'run()', 'init()', 'B'),
('Which datatype is used to store true or false?', 'int', 'boolean', 'char', 'float', 'B'),
('Which operator is used for comparison?', '=', '==', '!=', '++', 'B'),
('Which package contains Scanner class?', 'java.io', 'java.util', 'java.lang', 'java.sql', 'B'),
('Which symbol is used for single-line comments?', '/*', '//', '#', '<!--', 'B'),
('Which loop is guaranteed to execute at least once?', 'for', 'while', 'do-while', 'foreach', 'C'),
('Which exception occurs when dividing by zero?', 'NullPointerException', 'ArithmeticException', 'ArrayIndexOutOfBoundsException', 'IOException', 'B'),

('Which keyword is used to create an object?', 'class', 'new', 'void', 'static', 'B'),
('Which access modifier allows access everywhere?', 'private', 'protected', 'default', 'public', 'D'),
('Which keyword is used to prevent inheritance?', 'static', 'final', 'abstract', 'private', 'B'),
('Which keyword is used to call parent class constructor?', 'this', 'super', 'parent', 'extends', 'B'),
('Which of these is not OOP principle?', 'Encapsulation', 'Inheritance', 'Compilation', 'Polymorphism', 'C'),
('Which class is the parent of all classes?', 'Object', 'Main', 'System', 'Class', 'A'),
('Which method is used to print output?', 'print()', 'write()', 'println()', 'display()', 'C'),
('Which operator is used for logical AND?', '&', '&&', '|', '||', 'B'),
('Which keyword is used to define constant?', 'static', 'final', 'const', 'define', 'B'),
('Which loop is best when iterations are fixed?', 'while', 'do-while', 'for', 'foreach', 'C'),

('Which keyword is used to handle exceptions?', 'throw', 'throws', 'try-catch', 'exception', 'C'),
('Which block always executes?', 'try', 'catch', 'throw', 'finally', 'D'),
('Which keyword is used to create thread?', 'thread', 'run', 'start', 'new', 'D'),
('Which interface is used for threading?', 'Runnable', 'Serializable', 'Cloneable', 'Comparable', 'A'),
('Which collection does not allow duplicates?', 'List', 'Set', 'Map', 'Queue', 'B'),
('Which collection stores key-value pairs?', 'List', 'Set', 'Map', 'Queue', 'C'),
('Which method adds element to ArrayList?', 'insert()', 'add()', 'put()', 'push()', 'B'),
('Which class is used for input?', 'Scanner', 'System', 'Input', 'Reader', 'A'),
('Which keyword is used to exit loop?', 'break', 'stop', 'exit', 'return', 'A'),
('Which keyword skips current iteration?', 'break', 'continue', 'skip', 'pass', 'B'),

('Which package is imported by default?', 'java.io', 'java.util', 'java.lang', 'java.sql', 'C'),
('Which keyword is used to inherit interface?', 'extends', 'implements', 'inherits', 'interface', 'B'),
('Which access modifier is default?', 'public', 'private', 'protected', 'no keyword', 'D'),
('Which type of memory stores objects?', 'Stack', 'Heap', 'Register', 'Cache', 'B'),
('Which method compares strings?', '==', 'equals()', 'compare()', 'match()', 'B'),
('Which keyword is used to define abstract class?', 'virtual', 'abstract', 'interface', 'final', 'B'),
('Which operator increments value by 1?', '--', '++', '+=', '+', 'B'),
('Which data type stores decimal values?', 'int', 'float', 'boolean', 'char', 'B'),
('Which class is used for dates?', 'Date', 'Calendar', 'Time', 'Clock', 'A'),
('Which keyword is used to create package?', 'package', 'import', 'class', 'define', 'A'),

('Which file extension is used for Java?', '.jav', '.java', '.class', '.js', 'B'),
('Which command compiles Java code?', 'java', 'javac', 'compile', 'run', 'B'),
('Which command runs Java program?', 'javac', 'run', 'java', 'execute', 'C'),
('Which error occurs at runtime?', 'Syntax error', 'Logical error', 'Runtime error', 'Compile error', 'C'),
('Which keyword is used to create method?', 'function', 'method', 'void', 'define', 'C'),
('Which loop is entry-controlled?', 'do-while', 'while', 'for', 'both while and for', 'D'),
('Which type converts automatically?', 'Explicit', 'Implicit', 'Manual', 'Forced', 'B'),
('Which operator is ternary?', ':?', '? :', '??', '::', 'B'),
('Which keyword is used for inheritance?', 'inherits', 'extends', 'implements', 'super', 'B'),
('Which tool is used to build GUI?', 'AWT', 'Swing', 'JavaFX', 'All', 'D'),

('Which method starts thread?', 'run()', 'execute()', 'start()', 'begin()', 'C'),
('Which keyword is used to stop thread?', 'stop', 'end', 'exit', 'none', 'D'),
('Which collection is synchronized?', 'ArrayList', 'HashMap', 'Vector', 'HashSet', 'C'),
('Which exception is unchecked?', 'IOException', 'SQLException', 'RuntimeException', 'FileNotFoundException', 'C'),
('Which block handles exception?', 'try', 'catch', 'finally', 'throw', 'B'),
('Which statement throws exception?', 'throw', 'throws', 'try', 'catch', 'A'),
('Which keyword defines interface?', 'interface', 'implements', 'extends', 'abstract', 'A'),
('Which class reads input line?', 'Scanner', 'BufferedReader', 'InputStream', 'Reader', 'B'),
('Which symbol ends statement?', ':', ';', '.', ',', 'B'),
('Which keyword ends program?', 'exit', 'return', 'stop', 'end', 'B');

 
 select * from questions;
 SELECT COUNT(*) FROM questions;
 
SELECT * FROM questions
ORDER BY RAND()
LIMIT 10;


