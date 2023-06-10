# UMLify

This formal language, is designed for genereation of simple UML diagrams. The syntax is pretty similar with XML-like languages, that will also simplify the learning process of this another non-used project, made just for learning a new course at TUM.

So, as you can see in that sidebar from right, the project is written in Java. The decision was taken, because of the OOP, that will keep the best readability and logic in our programm, in order so even a school guy will understand what is written here.

## Dependecies

- OpenJDK 11+
- Maven
- OpenAI token ([generate it here](https://platform.openai.com/account/api-keys))

## Building

1) Clone project (you know how)
2) Open a normal Linux Terminal
3) Enter the directory and build:
    1) `cd uml/`
    2) `mvn clean compile assembly:single`
    3) `java -jar ./target/uml-1.0-SNAPSHOT-jar-with-dependencies.jar`
    4) And see how you'll receive an error. Follow next steps how to fix it
4) Inside `uml/` you can see the `.env` file. There you must drop your API key.
5) Enjoy our project 🤘
