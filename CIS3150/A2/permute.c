#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "adt/stringUtil.h"

void permute(char *, int, int);
void swap(char *, char *);

char ** alreadyDone = NULL;
int doneSize = 0;
int main()
{
	int i = 0;
	int n = 4;

	char * array = NULL;

	array = addCharToString(array,'0');
	for(i = 0; i < n; i ++)
	{
		array = addCharToString(array,(i +'0'));
	}
	permute(array,0,n);


	return 0;
}

void permute(char * array, int currentPosition, int size)
{
    int i;

    if (currentPosition == size)
    {
		if(checkIfStringExists(alreadyDone,doneSize,array) == 0)
		{
			printf("%s\n", array);
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



