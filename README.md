web-calculator
================
Web calculator  based on rest API

Functionality:
1) Add/Deduct/Multiply/Divide
2) Square/Root
3) Support brackets (/[/{
4) Support displaying calculation history
5) Calculate e^x integral*

*This function should take as the input the following values:
- The interval in which the integral should be calculated
- Number of threads in which this simple equation should be calculated. Relaying on the assumption that calculating integral within the given interval is the perfect task for multitasking, each thread calculates individual sub-interval and after that all results are basically merged
- Number of repeated calculations. In that field user can specify how many times the given task should be repeated. Each individual calculation should have separated order id

Technologies:

Frontend
-html,css,angularJS

Backend
-CDI,JAX-RS,
