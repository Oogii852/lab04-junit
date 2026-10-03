# Лаборатори №4: Нэгжийн тест — JUnit 5

- Оюутны нэр: Отгонсүх
- Оюутны код: B242270021

## Хувилбарууд

```
$ java -version
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment (build 17.0.20.1+1-1-26.04-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.20.1+1-1-26.04-Ubuntu, mixed mode, sharing)

$ mvn -version
[1mApache Maven 3.9.12[m
Maven home: /usr/share/maven
Java version: 17.0.20.1, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.18.33.2-microsoft-standard-wsl2", arch: "amd64", family: "unix"
```

## Үр дүн

- Тестийн методын тоо: 15
- results/mvn-test.txt-ийн "Tests run": 32
- Мутацид унасан тестийн тоо (Failures): 2

Мутацид унасан тестүүд:

```
GradeCalculatorTest.letterGradeBoundaries:128 expected: <A> but was: <B>
GradeCalculatorTest.ninetyIsExactlyA:34 expected: <A> but was: <B>
```

## Дүгнэлт

GradeCalculator классын letterGrade ба totalScore методуудад JUnit 5-аар 15 тестийн метод бичсэн бөгөөд CsvSource-ийн мөр бүрийг тусад нь тоолбол нийт 32 тест ногоон гарсан. Тест бүрийг Arrange–Act–Assert бүтцээр бичиж, @DisplayName-д монгол нэр өгсөн. Хязгаарын утгууд (90, 89.99, 60, 59.99, 0, 100) болон буруу оролтыг (-1, 101, att = -5, lab = 41) assertThrows-оор тусад нь шалгасан. Мутаци хийхдээ letterGrade доторх score >= 90 нөхцөлийг score > 90 болгоход 2 тест унаж BUILD FAILURE гарсан. Унасан тестүүд дээр жагсаасны дагуу 90 оноо A дүн авах ёстойг шалгадаг тестүүд байсан. Хамгийн сонирхолтой нь 95, 85, 75 зэрэг ердийн утгуудыг шалгадаг typicalValues тест мутацийг илрүүлж чадаагүй, зөвхөн хязгаарын тест л алдааг барьсан явдал юм. Иймээс pass болсон тест бүр зөв тест гэсэн үг биш гэдгийг ойлгосон. Мутацийг буцааж засаад mvn test-ийг дахин ажиллуулахад бүх тест ногоон болсон.
