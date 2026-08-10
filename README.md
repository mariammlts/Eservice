# Eservice

ავტომატიზაციის ტესტირების პროექტი საქართველოს შემოსავლების სამსახურის ელექტრონული სერვისების პორტალისთვის (**eservices.rs.ge**), დაწერილი **Java**-ზე, **Selenium WebDriver**-ისა და **TestNG**-ის გამოყენებით, **Page Object Model** მიხედვით.

## 🛠 გამოყენებული ტექნოლოგიები

- **Java**
- **Selenium WebDriver 4.27** — ბრაუზერის ავტომატიზაცია
- **WebDriverManager 5.9** — ბრაუზერის დრაივერების ავტომატური მართვა
- **TestNG 7.10** — ტესტების გაშვება, პარამეტრები და listener-ები
- **ExtentReports 5.1** — HTML ტესტ-რეპორტების გენერაცია
- **Maven** — პროექტის აწყობა და დამოკიდებულებების მართვა

## 📁 პროექტის სტრუქტურა

```
Eservice/
├── src/
│   ├── main/java/org/example/
│   │   ├── BasePage.java              # საერთო page object ლოგიკა
│   │   ├── Utils/
│   │   │   ├── DriverManager.java     # WebDriver-ის სასიცოცხლო ციკლის მართვა
│   │   │   ├── ConfigReader.java      # config.properties-ის წაკითხვა
│   │   │   ├── ExtentReportManager.java # ExtentReports-ის კონფიგურაცია
│   │   │   ├── TestListener.java      # TestNG listener (რეპორტინგისთვის)
│   │   │   └── Utils.java             # დამხმარე მეთოდები
│   │   └── pages/
│   │       ├── LoginPage.java         # ავტორიზაციის გვერდის page object
│   │       ├── ApplicationsPage.java  # განცხადებების გვერდის page object
│   │       └── InvoicesPage.java      # ანგარიშ-ფაქტურების გვერდის page object
│   └── test/java/org/example/
│       ├── BaseTest.java              # საბაზისო ტესტ-კლასი (setup/teardown)
│       └── tests/
│           ├── LoginTest.java         # ავტორიზაციის ტესტები
│           ├── ApplicationsTest.java  # განცხადების გაგზავნის ტესტი
│           └── InvoicesTest.java      # ანგარიშ-ფაქტურის შექმნის ტესტი
├── report/                            # გენერირებული ExtentReports რეპორტები
├── config.properties                  # ტესტ-მონაცემები და გარემოს კონფიგურაცია
├── testNG.xml                         # TestNG suite-ის კონფიგურაცია
└── pom.xml                            # Maven პროექტის კონფიგურაცია
```

## ✅ ტესტების დაფარვა

- **LoginTest** — სწორი ავტორიზაცია, ღილაკის ფერის შემოწმება, არასწორი პაროლი, ცარიელი პაროლის ვალიდაცია
- **ApplicationsTest** — განცხადების გაგზავნისა, სტატუსისა და აიდის დაბრუნება
- **InvoicesTest** — ანგარიშ-ფაქტურის შექმნა: მყიდველის საიდენტიფიკაციო კოდი, პროდუქტის დეტალები, გაგზავნა და სტატუსის შემოწმება

## 🚀 გაშვების ინსტრუქცია

### წინაპირობები

- დაინსტალირებული Java JDK
- დაინსტალირებული Maven
- Chrome ბრაუზერი

### ინსტალაცია

```bash
git clone https://github.com/mariammlts/Eservice.git
cd Eservice
mvn clean install
```

### კონფიგურაცია

ტესტ-მონაცემები და გარემოს პარამეტრები განსაზღვრულია `config.properties` ფაილში:

```properties
base.url=https://eservices.rs.ge/Login.aspx
user.name=...
user.password=...
```

გაშვებამდე განაახლე ეს მნიშვნელობები შენი ტესტ-გარემოს შესაბამისად.

### ტესტების გაშვება

სრული suite-ის გასაშვებად (იყენებს `testNG.xml`-ს):

```bash
mvn test
```

## 📊 რეპორტები

ტესტების შესრულების რეპორტები გენერირდება **ExtentReports**-ის საშუალებით და ინახება `report/` საქაღალდეში (`ExtentReport.html`) ყოველი გაშვების შემდეგ.
