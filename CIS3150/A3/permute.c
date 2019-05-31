#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "adt/stringUtil.h"
#include "Q3.h"

void permute(char *, int, int);
void swap(char *, char *);
int try();

char ** alreadyDone = NULL;
int doneSize = 0;

int main()
{
    try();
    /*
    array = addCharToString(array,'0');
    for(i = 0; i < n; i ++)
    {
        array = addCharToString(array,(i +'0'));
    }
    
    permute(array,0,n);
    */
	return 0;
}

void permute(char * array, int currentPosition, int size)
{
    int i;

    if (currentPosition == size)
    {
        if(checkIfStringExists(alreadyDone,doneSize,array) == 0)
        {
            /*printf("%s\n", array);*/
            clearStackA();
            clearStackB();
            clearStackC();

            if(runAlgo(array,(size-1)) == 1)
            {
                printf("String : %s\n",array);
            }
            alreadyDone = addStringToArray(alreadyDone,&doneSize,array);
        }     

    }
    else
    {
        for (i = currentPosition; i <= size; i++)
        {
            swap((array+currentPosition), (array+i));
            permute(array, currentPosition+1, size);
            swap((array+currentPosition), (array+i)); 
        }
    }
}


void swap(char * first, char * second)
{
    char temp;
    temp = * first;
    * first = * second;
    * second = temp;
}

/*
String : 00123
String : 03012
String : 30012
String : 32100
String : 32001


String : 001234
String : 040123
String : 400123
String : 432100
String : 432001
String : 430012
*/


int try()
{
    int i = 0;
    int n = 4; // 3

    char * array = NULL;

    array = addCharToString(array,'0');
    for(i = 0; i < n; i ++)
    {
        array = addCharToString(array,(i +'0'));
    }

    permute(array,0,n);
    free(array);

    n = 5; // 4
    array = NULL;

    return 1;
}