CREATE TABLE IF NOT EXISTS AND SELECT * User (
    id int NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE InvestmentPortfolio (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     stockName VARCHAR(255) NOT NULL,
                                     investmentAmount DOUBLE NOT NULL,
                                     currentValue DOUBLE NOT NULL,
                                     returns DOUBLE NOT NULL
);
