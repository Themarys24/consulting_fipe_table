# 🔍 FIPE Table - Vehicle Price Lookup

This project is a Java command-line application that allows you to check vehicle prices from the FIPE Table <img src="https://upload.wikimedia.org/wikipedia/en/0/05/Flag_of_Brazil.svg" width="18" alt="Brazil Flag">, a national reference for average vehicle prices in Brazil, widely used in negotiations and market evaluations.


## 📦 Project Structure
```
br.com.alura.project.FipeTable
├── main
│ └── Main.java # Main application entry point
├── model # Data models representing the API structure
│ ├── Brand.java
│ ├── Model.java
│ ├── Vehicle.java
│ └── YearOption.java
└── service # Logic for API communication
├── ApiConsumption.java
└── ConvertingData.java # (Reserved for data conversion utilities)
```

## ⚙️ Features

- Select vehicle type: car, motorcycle, or truck
- List all available brands
- View available models by brand
- List available years for a selected model
- Display market price values for each year/model combination

## 🧪 Example Usage

*** OPTIONS ***
1 - Car
2 - Motorcycle
3 - Truck

Enter one of the options (number or name): car

Available brands:
21 - Fiat
22 - Ford
...

Enter the brand code for consultation:
21

Available models:
4828 - Argo
4945 - Cronos
...

Enter the model code to check values:
4828

Checking values for available years:
2023 Gasoline -> 2023 - Fiat Argo - Gasoline - R$ 75,890.00
2022 Gasoline -> 2022 - Fiat Argo - Gasoline - R$ 69,320.00


🌐 About the FIPE API
This application consumes data from the public API provided by Portal FIPE, which offers up-to-date information on vehicles in categories such as cars, motorcycles, and trucks.

Base URL: https://parallelum.com.br/fipe/api/v1

Requests follow the REST standard using Java's built-in HttpClient (Java 11+)

🛠 Technologies Used
Java 17+

Gson library for JSON parsing

HttpClient (java.net.http) for making HTTP requests

Maven/Gradle (optional for dependency management)


💡 How to Run
Clone the repository:
git clone https://github.com/your-username/your-repository.git

Compile and run:
javac -d out src/br/com/alura/project/FipeTable/**/*.java
java -cp out br.com.alura.project.FipeTable.main.Main

📚 Credits
This project was developed as part of study activities based on the course provided by Alura on consuming APIs with Java.


