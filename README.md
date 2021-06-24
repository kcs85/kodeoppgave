
# Fetching job ads from NAVs public API

## Introduction
This repo fetches job ads from the past 6 months from Navs job ads public API (described here https://github.com/navikt/pam-public-feed)
The number of job ads with the words "kotlin" and "java" are then grouped per week, and outputted in the terminal. 

## Technology
The repo is build using Spring Boot, Maven and Kotlin. 

## Solution

### 1. Fetching the data
To start with, the data was fetched from the public API with a public token provided as authorization. 
As the number of hits was limited to 1000 per request, it was necessary to split the date ranges into several segments.
Today's date was the starting point, and then going backwards in time, the published date of the final ad was the set as a new starting date, and so on until all the ads were fetched until 6 months before todays date. 
Each starting date was also divided up into 10 pages, with 100 hits per page. 

### 2. Modifying the data
All job ads were concatinated into one list, and all ads published before 6 moths were excluded from the list. 
Next, a search was done in the description of each ad for the words "kotlin" and "java".
The number of words were then counted and grouped by week. 

### 3. Showing the results
A prettified json was then printed to the terminal for both java and kotlin, showing the week number and the number of ads containing the key word. 
The results revealed the following:
```
Java : week and number of ad
{
"25" : 7,
"24" : 9,
"23" : 15,
"22" : 11,
"21" : 4,
"20" : 5,
"19" : 2,
"18" : 1,
"17" : 3,
"16" : 1,
"14" : 1,
"8" : 1,
"1" : 1
}
Kotlin : week and number of ads
{
"24" : 1,
"23" : 4,
"22" : 3,
"21" : 1,
"8" : 1
}
```

## Setting it up
To test out the solution for yourself, open the application in IntelliJ and run KodeoppgaveApplication.kt. 
This will host a server listening on http://localhost:8080/. To start the process, go to http://localhost:8080/getads and it should fetch all the jobs. 
After a few seconds you should see the results in the command line.



