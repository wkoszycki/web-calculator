Status:
[![Build Status](https://travis-ci.org/wkoszycki/web-calculator.png?branch=master)](https://travis-ci.org/wkoszycki/web-calculator)

[![Coverage Status](https://coveralls.io/repos/wkoszycki/web-calculator/badge.svg)](https://coveralls.io/r/wkoszycki/web-calculator)


web-calculator
================
Web calculator  based on rest API

Functionality:

1) Add/Deduct/Multiply/Divide

2) Square/Root

3) Support brackets ( / [ / {

4) Support displaying calculation history

5) Calculate e^x integral* in asynchronous multi threading manner


Technologies:

Frontend
-html,css,angularJS

Backend
-CDI,JAX-RS,


How to run:
================

build with Maven :

mvn package

Download jetty-runner-8.1.9.v20130131.jar

execute java -jar jetty-runner.jar web-calculator.war


