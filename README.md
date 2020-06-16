# comprehension
Comprehending Data

Project Perquisite :
1. Maven
2. jdk 8  or higher
3. lombok IDE plugin

Steps to Run:
1. Open file RawInputFileTest.java (\src\test\java\unit\input\RawInputFileTest.java)
2. Run Method 'processData()'
3. OR Just run Main file (\src\main\java\main\Main.java)
3. Inputs:
        
        1. File Based
            - This is configuration file : (src\main\resources\configuration.yml)
                - provide paragraph fileName, questionFileName, AnswerFileName and charset
                - paragraph, question, answer files should be in project root location  
            - File name to be give in the configuration.yml
        
4. Output: file name 'ComprehensionOutput' at project root location