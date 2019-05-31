#include "stringUtil.h"


char * addCharToString(char * string, char toAdd)
{
	int length = 0;
	if(string != NULL)
	{
		length = strlen(string);
	}
	
	string = realloc(string,sizeof(char) * (length + 2));
	
	string[length] = toAdd;
	string[length+1] = '\0';

	return string;
}

char ** toStringArray(char * string, char deliminator, int * numElements)
{
	char ** array = NULL;
	char * element = NULL;
	*numElements = 0;
	int length = strlen(string);


	int i = 0;
	for(i = 0; i < length; i ++)
	{
		if(string[i] != deliminator)
		{
			element = addCharToString(element,string[i]);
		}
		else
		{
			if(element != NULL)
			{
				array = realloc(array,(sizeof(char *) * ( (*numElements) + 1)));
				array[(*numElements)] = malloc(strlen(element)+1);
				
				strcpy(array[(*numElements)], element);
				array[(*numElements)][strlen(element)+1] = '\0';
				(*numElements) ++;
				free(element);
				element = NULL;
			}
		}
	}
	return array;
}


char ** addStringToArray(char ** array, int * arraySize, char * string)
{
	array = realloc(array,(sizeof(char *) * ( (*arraySize) + 1)));
	array[(*arraySize)] = malloc(strlen(string)+1);
				
	strcpy(array[(*arraySize)], string);
	(*arraySize) ++;
	return array;
}

int checkIfStringExists(char ** array, int size, char * string)
{
	int i = 0;
	for(i = 0; i < size; i ++)
	{
		if(strcmp(array[i],string) == 0)
		{
			return 1;
		}
	}
	return 0;
}
